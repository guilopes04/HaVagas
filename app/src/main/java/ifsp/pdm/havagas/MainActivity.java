package ifsp.pdm.havagas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import ifsp.pdm.havagas.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding amb;
    private Form form;
    private static final String TAG = "CICLO_PDM_TAG";
    private final static String KEY_NOME = "KEY_NOME";
    private final static String KEY_EMAIL = "KEY_EMAIL";
    private final static String KEY_TELEFONE = "KEY_TELEFONE";
    private final static String KEY_CELULAR = "KEY_CELULAR";
    private final static String KEY_SEXO = "KEY_SEXO";
    private final static String KEY_DATANASC = "KEY_DATANASC";
    private final static String KEY_FORMATURA = "KEY_FORMATURA";
    private final static String KEY_VAGAINT = "KEY_VAGAINT";
    private final static String KEY_INSTITUICAO = "KEY_INSTITUICAO";
    private final static String KEY_TITULO_MONO = "KEY_TITULO_MONO";
    private final static String KEY_ORIENTADOR_MONO = "KEY_ORIENTADOR_MONO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amb = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(amb.getRoot());

        amb.limparBt.setOnClickListener(view -> {
            amb.nomeEt.setText("");
            amb.emailEt.setText("");
            amb.receberEmail.setChecked(false);
            amb.telefoneEt.setText("");
            amb.celularOpCb.setChecked(false);
            amb.celularEt.setVisibility(View.GONE);
            amb.celularEt.setText("");
            if (!amb.telefoneComercialRd.isChecked()){
                amb.telefoneComercialRd.setChecked(true);
            }
            amb.formacaoSp.setSelection(0);
            amb.telefoneComercialRd.setChecked(true);
            amb.anoFormatura.setText("");
            amb.anoConclusao.setText("");
            amb.instituicao.setText("");
            amb.tituloMonografia.setText("");
            amb.orientadorMonografia.setText("");
            amb.vagasInteresse.setText("");
            amb.dataNascimento.setText("");
            amb.femininoRd.setChecked(true);
            amb.anoFormatura.setVisibility(View.GONE);
            amb.anoConclusao.setVisibility(View.GONE);
            amb.instituicao.setVisibility(View.GONE);
            amb.tituloMonografia.setVisibility(View.GONE);
            amb.orientadorMonografia.setVisibility(View.GONE);
            form = null;
        });

        amb.salvarBt.setOnClickListener(view -> {
            Form form = new Form();
            form.setName(amb.nomeEt.getText().toString());
            form.setEmail(amb.emailEt.getText().toString());
            form.setReceiveEmailOption(amb.receberEmail.isChecked() ? "Yes" : "No");
            form.setPhone(amb.telefoneEt.getText().toString());
            form.setResidentialCommercialPhone(amb.telefoneComercialRd.isChecked() ? getString(R.string.telefoneComercial) : getString(R.string.telefoneResidencial));
            form.setGender(amb.femininoRd.isChecked() ? getString(R.string.feminino) : getString(R.string.masculino));
            form.setBirthDate(amb.dataNascimento.getText().toString());
            form.setCell(amb.celularEt.getText().toString());
            form.setEducation(amb.formacaoSp.getSelectedItem().toString());
            form.setInterestPositions(amb.vagasInteresse.getText().toString());
            form.setGraduationYear(amb.anoFormatura.getText().toString());
            form.setCompletionYear(amb.anoConclusao.getText().toString());
            form.setEducationInstitution(amb.instituicao.getText().toString());
            form.setThesisTitle(amb.tituloMonografia.getText().toString());
            form.setThesisAdvisor(amb.orientadorMonografia.getText().toString());
            
            Toast.makeText(this, form.toString(), Toast.LENGTH_SHORT).show();
        });

        amb.formacaoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 || position == 1){
                    amb.anoFormatura.setVisibility(View.VISIBLE);
                } else {
                    amb.anoFormatura.setVisibility(View.GONE);
                }
                if (position == 2 || position == 3){
                    amb.anoConclusao.setVisibility(View.VISIBLE);
                    amb.instituicao.setVisibility(View.VISIBLE);
                } else {
                    amb.anoConclusao.setVisibility(View.GONE);
                    amb.instituicao.setVisibility(View.GONE);
                }
                if (position == 4 || position == 5 ){
                    amb.anoConclusao.setVisibility(View.VISIBLE);
                    amb.instituicao.setVisibility(View.VISIBLE);
                    amb.tituloMonografia.setVisibility(View.VISIBLE);
                    amb.orientadorMonografia.setVisibility(View.VISIBLE);
                } else {
                    amb.tituloMonografia.setVisibility(View.GONE);
                    amb.orientadorMonografia.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        amb.celularOpCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (amb.celularOpCb.isChecked()){
                    amb.celularEt.setVisibility(View.VISIBLE);
                } else {
                    amb.celularEt.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_NOME, amb.nomeEt.getText().toString());
        outState.putString(KEY_EMAIL, amb.emailEt.getText().toString());
        outState.putString(KEY_TELEFONE, amb.telefoneEt.getText().toString());
        outState.putString(KEY_CELULAR, amb.celularEt.getText().toString());
        outState.putString(KEY_SEXO, amb.femininoRd.isChecked() ? "Feminino" : "Masculino");
        outState.putString(KEY_DATANASC, amb.dataNascimento.getText().toString());
        outState.putString(KEY_FORMATURA, amb.anoFormatura.getText().toString());
        outState.putString(KEY_VAGAINT, amb.vagasInteresse.getText().toString());
        outState.putString(KEY_INSTITUICAO, amb.instituicao.getText().toString());
        outState.putString(KEY_TITULO_MONO, amb.tituloMonografia.getText().toString());
        outState.putString(KEY_ORIENTADOR_MONO, amb.orientadorMonografia.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        amb.nomeEt.setText(savedInstanceState.getString(KEY_NOME, ""));
        amb.emailEt.setText(savedInstanceState.getString(KEY_EMAIL, ""));
        amb.telefoneEt.setText(savedInstanceState.getString(KEY_TELEFONE, ""));
        amb.celularEt.setText(savedInstanceState.getString(KEY_CELULAR, ""));
        amb.anoFormatura.setText(savedInstanceState.getString(KEY_FORMATURA, ""));
        amb.instituicao.setText(savedInstanceState.getString(KEY_INSTITUICAO, ""));
        amb.tituloMonografia.setText(savedInstanceState.getString(KEY_TITULO_MONO, ""));
        amb.orientadorMonografia.setText(savedInstanceState.getString(KEY_ORIENTADOR_MONO, ""));
        amb.vagasInteresse.setText(savedInstanceState.getString(KEY_VAGAINT, ""));
        amb.dataNascimento.setText(savedInstanceState.getString(KEY_DATANASC, ""));
        if(savedInstanceState.getString(KEY_SEXO, "") == "Feminino"){
            amb.femininoRd.setChecked(true);
        }else{
            amb.masculinoRd.setChecked(true);
        }

    }


}