package com.example.phoenix.alzhfinder;

import android.support.design.widget.TabLayout;
//import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PantallaPrincipal extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabs;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        crearHeader();
        crearMenu();

        fragment = new Pacientes();
        actualizarVista();

        menuListener();

    }

    /**
     * Agregamos una toolbar personalizada con la clase Toolbar
     * asignada al toolbar del Layout "activity_pantalla_principal"
     */
    public void crearHeader(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //Si existe el toolbar entonces agregamos algunas configuraciones
        if(this.toolbar != null) {
            setSupportActionBar(toolbar); //convertimos el toolbar en un actionbar
            //Le asignamos el nombre de la App desde values/strings
            getSupportActionBar().setTitle(R.string.app_name);
            //getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void crearMenu(){
        /**
         * Agregamos el soporte para tabs, dentro de nuestra toolbar personalizada
         * con nombre tabs, y la asignamos al xml tabs del layout principal
         */
        tabs = (TabLayout) findViewById(R.id.tabs);

        /**
         * Agregamos cada tab de forma separada y le asignamos un ícono
         * desde el directorio drawable
         */
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_lista_pacientes));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_maps));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_ficha_paciente));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_settings));
    }

    /**
     * Actualizamos la Vista de la pestaña, eso pasará cada vez que se presione
     * en algún ícono del menú
     */
    public void actualizarVista(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    /**
     * Quedamos a la escucha de que botón se presiona en el menú
     * Dependiendo del caso se muestra una vista u otra
     */
    public void menuListener(){
        tabs.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        switch (tab.getPosition()){
                            case 0:
                                fragment = new Pacientes();
                                actualizarVista();
                                break;
                            case 1:
                                fragment = new Mapa();
                                actualizarVista();
                                break;
                            case 2:
                                fragment = new InformacionPaciente();
                                actualizarVista();
                                break;
                            case 3:
                                fragment = new Settings();
                                actualizarVista();
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        // ...
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        // ...
                    }
                }
        );
    }
}
