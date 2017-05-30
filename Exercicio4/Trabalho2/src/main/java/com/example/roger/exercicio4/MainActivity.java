package com.example.roger.exercicio4;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imgFilme;
    private FloatingActionButton fabBuscarImagem, fabProximo, fabAnterior;
    private ArrayList<Filme> filmes;
    private ArrayList<Diretor> diretores;
    private Spinner spinnerDiretores;
    private Bitmap bitmap;
    private EditText edtFilme, edtCodigo, edtAno, edtDataLancamento;
    private RatingBar ratBarra;
    private int posicao;
    private static Integer ACTIVITY_LISTA = 123;
    private static Integer BUSCA_IMAGEM = 234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabBuscarImagem = (FloatingActionButton) findViewById(R.id.fabBuscaFilme);
        fabProximo = (FloatingActionButton) findViewById(R.id.fabProximo);
        fabAnterior = (FloatingActionButton) findViewById(R.id.fabAnterior);
        fabBuscarImagem.setOnClickListener(clique);
        fabProximo.setOnClickListener(clique);
        fabAnterior.setOnClickListener(clique);
        imgFilme = (ImageView) findViewById(R.id.imgFilme);
        spinnerDiretores = (Spinner) findViewById(R.id.mainActivitySpinnerDiretores);

        edtAno = (EditText) findViewById(R.id.edtAno);
        edtCodigo = (EditText) findViewById(R.id.edtCodigo);
        edtDataLancamento = (EditText) findViewById(R.id.edtDataLancamento);
        edtFilme = (EditText) findViewById(R.id.edtFilme);
        ratBarra = (RatingBar) findViewById(R.id.ratBarra);

        if ((diretores != null) && (diretores.size() > 0)) {
            populaDiretor();
        }
        posicao = 0;


    }

    View.OnClickListener clique = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if ((filmes != null)  &&(filmes.size() > 0)) {
                if (v == fabBuscarImagem)
                    pickImage();
                else if (v == fabAnterior) {
                    avancaFilme(false);
                } else if (v == fabProximo) {
                    avancaFilme(true);
                }
            }
        }
    };

    private void avancaFilme(boolean avanca) {
        if (avanca) {
            posicao++;
            if (posicao >= filmes.size())
                posicao = 0;
        } else {
            posicao--;
            if (posicao < 0) {
                posicao = filmes.size() - 1;
            }
        }
        montaCamposTela();
    }

    private void montaCamposTela() {
        if (filmes.size() > 0) {
            filmes.get(posicao);
            if (filmes.get(posicao) != null) {
                if (filmes.get(posicao).getAno() != null) {
                    edtAno.setText(filmes.get(posicao).getAno());
                }
                if (filmes.get(posicao).getCodigo() != null) {
                    edtCodigo.setText(filmes.get(posicao).getCodigo());
                }
                if (filmes.get(posicao).getDataLancamento() != null) {
                    edtDataLancamento.setText(filmes.get(posicao).getDataLancamento());
                }
                if (filmes.get(posicao).getFilme() != null) {
                    edtFilme.setText(filmes.get(posicao).getFilme());
                }
                if (filmes.get(posicao).getBytes() != null) {
                    byte[] byteArray = filmes.get(posicao).getBytes();
                    imgFilme.setImageBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
                }
                if (filmes.get(posicao).getDiretor() != null) {
                    spinnerDiretores.setSelection(filmes.get(posicao).getDiretor().getId());
                }
                if (filmes.get(posicao).getRating() != null) {
                    ratBarra.setRating(filmes.get(posicao).getRating());
                } else {
                    ratBarra.setRating(0);
                }
            }
        } else {
            limpaCampos();
        }

    }

    private void populaDiretor() {
        String[] dirs = new String[diretores.size()];
        for (int i = 0; i < diretores.size(); i++) {
            dirs[i] = diretores.get(i).getNome();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dirs);
        spinnerDiretores.setAdapter(adapter);
    }

    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, BUSCA_IMAGEM);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();


        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuListar) {
            Intent intent = new Intent(MainActivity.this, ActivityListaFilmes.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("lista", filmes);
            intent.putExtras(bundle);
            startActivityForResult(intent, ACTIVITY_LISTA);

        } else if (item.getItemId() == R.id.menuAdicionarDiretor) {
            final AlertDialog.Builder buider = new AlertDialog.Builder(MainActivity.this);
            buider.setTitle("Novo Diretor");
            LayoutInflater inflater = MainActivity.this.getLayoutInflater();
            final View view = inflater.inflate(R.layout.cadastrodiretor, null);
            buider.setView(view);
            buider.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText edtNome = (EditText) view.findViewById(R.id.cadastroDiretorEditNome);
                    if (diretores == null) {
                        diretores = new ArrayList<Diretor>();
                    }
                    diretores.add(new Diretor(edtNome.getText().toString(), diretores.size()));
                    populaDiretor();
                }
            });
            buider.setNegativeButton("Cancelar", null);
            buider.show();

        } else if (item.getItemId() == R.id.menuSalvar) {
            if (validaCampos()) {
                if (posicao < filmes.size()) {
                    Filme filme = filmes.get(posicao);
                    filme = montaCamposFilme(filme);
                    filmes.set(posicao, filme);
                }
                montaCamposTela();
                Toast.makeText(this, "Filme salvo com sucesso!", Toast.LENGTH_SHORT).show();
            }

        } else if (item.getItemId() == R.id.menuExcluir) {
            filmes.remove(posicao);
            if (posicao > 0) {
                posicao--;
            }
            montaCamposTela();
            Toast.makeText(this, "Filme excluído com sucesso!", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menuNovo) {
            limpaCampos();
            Filme filme = new Filme();
            if (filmes == null)
                filmes = new ArrayList<>();
            filmes.add(filme);
            posicao = filmes.size() - 1;
        }
        return super.onOptionsItemSelected(item);
    }

    private void limpaCampos() {
        edtFilme.setText("");
        edtDataLancamento.setText("");
        edtCodigo.setText("");
        edtAno.setText("");
        imgFilme.setImageResource(R.drawable.noimg);
        ratBarra.setRating(0);
        posicao = filmes != null ? filmes.size() : 0;
    }

    private Filme montaCamposFilme(Filme filme) {
        filme.setAno(edtAno.getText().toString());
        filme.setCodigo(edtCodigo.getText().toString());
        filme.setDataLancamento(edtDataLancamento.getText().toString());
        filme.setFilme(edtFilme.getText().toString());
        filme.setRating(ratBarra.getRating());
        filme.setDiretor(diretores.get(spinnerDiretores.getSelectedItemPosition()));
        return filme;
    }

    private boolean validaCampos() {
        String mensagem = "";

        if (edtAno.getText().toString().isEmpty()) {
            mensagem += "Ano, ";
        }
        if (edtCodigo.getText().toString().isEmpty()) {
            mensagem += "Codigo, ";
        }
        if (edtDataLancamento.getText().toString().isEmpty()) {
            mensagem += "Data de lançamento, ";
        }
        if (edtFilme.getText().toString().isEmpty()) {
            mensagem += "Filme, ";
        }
        if (spinnerDiretores.getSelectedItem() == null) {
            mensagem += "Diretor, ";
        }
        if (!mensagem.isEmpty()) {
            Toast.makeText(this, "Os seguintes campos estão vazios: " + mensagem.substring(0, mensagem.length() - 2) + "!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BUSCA_IMAGEM && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            } else {
                if (data != null) {
                    try {

                        InputStream stream = getContentResolver().openInputStream(
                                data.getData());
                        bitmap = BitmapFactory.decodeStream(stream);
                        stream.close();
                        bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
                        imgFilme.setImageBitmap(bitmap);
                        if (filmes.get(posicao) != null)
                            filmes.get(posicao).setBytes(returnByte(bitmap));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if ((requestCode == ACTIVITY_LISTA) && (resultCode == RESULT_OK)) {
            filmes = (ArrayList<Filme>) data.getExtras().getSerializable("lista");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (bitmap != null) {
            byte[] byteArray = returnByte(bitmap);
            savedInstanceState.putByteArray("imagem", byteArray);
        }
        if (filmes != null) {
            savedInstanceState.putSerializable("filmes", filmes);
        }
        if (diretores != null) {
            savedInstanceState.putSerializable("diretores", diretores);
        }
    }

    private byte[] returnByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        byte[] byteArray = savedInstanceState.getByteArray("imagem");

        if (byteArray != null) {
            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imgFilme.setImageBitmap(bitmap);
        }
        filmes = (ArrayList<Filme>) savedInstanceState.getSerializable("filmes");
        diretores = (ArrayList<Diretor>) savedInstanceState.getSerializable("diretores");
        if ((diretores != null) && (diretores.size() > 0)) {
            populaDiretor();
        }
    }
}
