package com.fs.btproject2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ImageView b11, b12, b13, b14, b21, b22, b23, b24, b31, b32, b33, b34, b41, b42, b43, b44;
    ImageView b51, b52, b53, b54, b61, b62, b63, b64, b71, b72, b73, b74, b81, b82, b83, b84;
    ImageView b91, b92, b93, b94, b101, b102, b103, b104, b111, b112, b113, b114, b121, b122, b123, b124;
    ImageView b131, b132, b133, b134, b141, b142, b143, b144;
    Button reset;
    boolean c11 = false, c12 = false, c13 = false, c14 = false,
            c21 = false, c22 = false, c23 = false, c24 = false,
            c31 = false, c32 = false, c33 = false, c34 = false,
            c41 = false, c42 = false, c43 = false, c44 = false,
            c51 = false, c52 = false, c53 = false, c54 = false,
            c61 = false, c62 = false, c63 = false, c64 = false,
            c71 = false, c72 = false, c73 = false, c74 = false,
            c81 = false, c82 = false, c83 = false, c84 = false,
            c91 = false, c92 = false, c93 = false, c94 = false,
            c101 = false, c102 = false, c103 = false, c104 = false,
            c111 = false, c112 = false, c113 = false, c114 = false,
            c121 = false, c122 = false, c123 = false, c124 = false,
            c131 = false, c132 = false, c133 = false, c134 = false,
            c141 = false, c142 = false, c143 = false, c144 = false;

    //-------------------------------------------
    Handler bluetoothIn;
    final int handlerState = 0;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder DataStringIN = new StringBuilder();
    private ConnectedThread MyConexionBT;
    // Identificador unico de servicio - SPP UUID
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // String para la direccion MAC
    private static String address = null;
    //-------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        reset = (Button) findViewById(R.id.reset);
        b11 = (ImageView) findViewById(R.id.b11);
        b12 = (ImageView) findViewById(R.id.b12);
        b13 = (ImageView) findViewById(R.id.b13);
        b14 = (ImageView) findViewById(R.id.b14);
        b21 = (ImageView) findViewById(R.id.b21);
        b22 = (ImageView) findViewById(R.id.b22);
        b23 = (ImageView) findViewById(R.id.b23);
        b24 = (ImageView) findViewById(R.id.b24);
        b31 = (ImageView) findViewById(R.id.b31);
        b32 = (ImageView) findViewById(R.id.b32);
        b33 = (ImageView) findViewById(R.id.b33);
        b34 = (ImageView) findViewById(R.id.b34);
        b41 = (ImageView) findViewById(R.id.b41);
        b42 = (ImageView) findViewById(R.id.b42);
        b43 = (ImageView) findViewById(R.id.b43);
        b44 = (ImageView) findViewById(R.id.b44);
        b51 = (ImageView) findViewById(R.id.b51);
        b52 = (ImageView) findViewById(R.id.b52);
        b53 = (ImageView) findViewById(R.id.b53);
        b54 = (ImageView) findViewById(R.id.b54);
        b61 = (ImageView) findViewById(R.id.b61);
        b62 = (ImageView) findViewById(R.id.b62);
        b63 = (ImageView) findViewById(R.id.b63);
        b64 = (ImageView) findViewById(R.id.b64);
        b71 = (ImageView) findViewById(R.id.b71);
        b72 = (ImageView) findViewById(R.id.b72);
        b73 = (ImageView) findViewById(R.id.b73);
        b74 = (ImageView) findViewById(R.id.b74);
        b81 = (ImageView) findViewById(R.id.b81);
        b82 = (ImageView) findViewById(R.id.b82);
        b83 = (ImageView) findViewById(R.id.b83);
        b84 = (ImageView) findViewById(R.id.b84);
        b91 = (ImageView) findViewById(R.id.b91);
        b92 = (ImageView) findViewById(R.id.b92);
        b93 = (ImageView) findViewById(R.id.b93);
        b94 = (ImageView) findViewById(R.id.b94);
        b101 = (ImageView) findViewById(R.id.b101);
        b102 = (ImageView) findViewById(R.id.b102);
        b103 = (ImageView) findViewById(R.id.b103);
        b104 = (ImageView) findViewById(R.id.b104);
        b111 = (ImageView) findViewById(R.id.b111);
        b112 = (ImageView) findViewById(R.id.b112);
        b113 = (ImageView) findViewById(R.id.b113);
        b114 = (ImageView) findViewById(R.id.b114);
        b121 = (ImageView) findViewById(R.id.b121);
        b122 = (ImageView) findViewById(R.id.b122);
        b123 = (ImageView) findViewById(R.id.b123);
        b124 = (ImageView) findViewById(R.id.b124);
        b131 = (ImageView) findViewById(R.id.b131);
        b132 = (ImageView) findViewById(R.id.b132);
        b133 = (ImageView) findViewById(R.id.b133);
        b134 = (ImageView) findViewById(R.id.b134);
        b141 = (ImageView) findViewById(R.id.b141);
        b142 = (ImageView) findViewById(R.id.b142);
        b143 = (ImageView) findViewById(R.id.b143);
        b144 = (ImageView) findViewById(R.id.b144);

        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    DataStringIN.append(readMessage);

                    int endOfLineIndex = DataStringIN.indexOf("#");

                    if (endOfLineIndex > 0) {
                        String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        //IdBufferIn.setText("Dato: " + dataInPrint);//<-<- PARTE A MODIFICAR >->->
                        DataStringIN.delete(0, DataStringIN.length());
                    }
                }
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter(); // get Bluetooth adapter
        VerificarEstadoBT();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("res");
                b11.setImageResource(R.drawable.boff2_opt);
                b12.setImageResource(R.drawable.boff2_opt);
                b13.setImageResource(R.drawable.boff2_opt);
                b14.setImageResource(R.drawable.boff2_opt);
                b21.setImageResource(R.drawable.boff2_opt);
                b22.setImageResource(R.drawable.boff2_opt);
                b23.setImageResource(R.drawable.boff2_opt);
                b24.setImageResource(R.drawable.boff2_opt);
                b31.setImageResource(R.drawable.boff2_opt);
                b32.setImageResource(R.drawable.boff2_opt);
                b33.setImageResource(R.drawable.boff2_opt);
                b34.setImageResource(R.drawable.boff2_opt);
                b41.setImageResource(R.drawable.boff2_opt);
                b42.setImageResource(R.drawable.boff2_opt);
                b43.setImageResource(R.drawable.boff2_opt);
                b44.setImageResource(R.drawable.boff2_opt);
                b51.setImageResource(R.drawable.boff2_opt);
                b52.setImageResource(R.drawable.boff2_opt);
                b53.setImageResource(R.drawable.boff2_opt);
                b54.setImageResource(R.drawable.boff2_opt);
                b61.setImageResource(R.drawable.boff2_opt);
                b62.setImageResource(R.drawable.boff2_opt);
                b63.setImageResource(R.drawable.boff2_opt);
                b64.setImageResource(R.drawable.boff2_opt);
                b71.setImageResource(R.drawable.boff2_opt);
                b72.setImageResource(R.drawable.boff2_opt);
                b73.setImageResource(R.drawable.boff2_opt);
                b74.setImageResource(R.drawable.boff2_opt);
                b81.setImageResource(R.drawable.boff2_opt);
                b82.setImageResource(R.drawable.boff2_opt);
                b83.setImageResource(R.drawable.boff2_opt);
                b84.setImageResource(R.drawable.boff2_opt);
                b91.setImageResource(R.drawable.boff2_opt);
                b92.setImageResource(R.drawable.boff2_opt);
                b93.setImageResource(R.drawable.boff2_opt);
                b94.setImageResource(R.drawable.boff2_opt);
                b101.setImageResource(R.drawable.boff2_opt);
                b102.setImageResource(R.drawable.boff2_opt);
                b103.setImageResource(R.drawable.boff2_opt);
                b104.setImageResource(R.drawable.boff2_opt);
                b111.setImageResource(R.drawable.boff2_opt);
                b112.setImageResource(R.drawable.boff2_opt);
                b113.setImageResource(R.drawable.boff2_opt);
                b114.setImageResource(R.drawable.boff2_opt);
                b121.setImageResource(R.drawable.boff2_opt);
                b122.setImageResource(R.drawable.boff2_opt);
                b123.setImageResource(R.drawable.boff2_opt);
                b124.setImageResource(R.drawable.boff2_opt);
                b131.setImageResource(R.drawable.boff2_opt);
                b132.setImageResource(R.drawable.boff2_opt);
                b133.setImageResource(R.drawable.boff2_opt);
                b134.setImageResource(R.drawable.boff2_opt);
                b141.setImageResource(R.drawable.boff2_opt);
                b142.setImageResource(R.drawable.boff2_opt);
                b143.setImageResource(R.drawable.boff2_opt);
                b144.setImageResource(R.drawable.boff2_opt);

                c11 = c12 = c13 = c14 = c21 = c22 = c23 = c24 = c31 = c32 = c33 = c34 = c41 = c42 = c43 = c44 = false;
                c51 = c52 = c53 = c54 = c61 = c62 = c63 = c64 = c71 = c72 = c73 = c74 = c81 = c82 = c83 = c84 = false;
                c91 = c92 = c93 = c94 = c101 = c102 = c103 = c104 = false;
                c111 = c112 = c113 = c114 = c121 = c122 = c123 = c124 = false;
                c121 = c122 = c123 = c124 = c131 = c132 = c133 = c134 = false;
            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("001");
                if(c11 == false){
                    b11.setImageResource(R.drawable.bon2_opt);
                    //MyConexionBT.write("1001");
                    c11 = true;
                }
                else{
                    b11.setImageResource(R.drawable.boff2_opt);
                    //MyConexionBT.write("1000");
                    c11 = false;
                }
            }
        });

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("002");
                if(c12 == false){
                    b12.setImageResource(R.drawable.bon2_opt);
                    c12 = true;
                }
                else{
                    b12.setImageResource(R.drawable.boff2_opt);
                    c12 = false;
                }
            }
        });

        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("003");
                if(c13 == false){
                    b13.setImageResource(R.drawable.bon2_opt);
                    c13 = true;
                }
                else{
                    b13.setImageResource(R.drawable.boff2_opt);
                    c13 = false;
                }
            }
        });

        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("004");
                if(c14 == false){
                    b14.setImageResource(R.drawable.bon2_opt);
                    c14 = true;
                }
                else{
                    b14.setImageResource(R.drawable.boff2_opt);
                    c14 = false;
                }
            }
        });

        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("005");
                if(c21 == false){
                    b21.setImageResource(R.drawable.bon2_opt);
                    c21 = true;
                }
                else{
                    b21.setImageResource(R.drawable.boff2_opt);
                    c21 = false;
                }
            }
        });

        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("006");
                if(c22 == false){
                    b22.setImageResource(R.drawable.bon2_opt);
                    c22 = true;
                }
                else{
                    b22.setImageResource(R.drawable.boff2_opt);
                    c22 = false;
                }
            }
        });

        b23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("007");
                if(c23 == false){
                    b23.setImageResource(R.drawable.bon2_opt);
                    c23 = true;
                }
                else{
                    b23.setImageResource(R.drawable.boff2_opt);
                    c23 = false;
                }
            }
        });

        b24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("008");
                if(c24 == false){
                    b24.setImageResource(R.drawable.bon2_opt);
                    c24 = true;
                }
                else{
                    b24.setImageResource(R.drawable.boff2_opt);
                    c24 = false;
                }
            }
        });

        b31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("009");
                if(c31 == false){
                    b31.setImageResource(R.drawable.bon2_opt);
                    c31 = true;
                }
                else{
                    b31.setImageResource(R.drawable.boff2_opt);
                    c31 = false;
                }
            }
        });

        b32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("010");
                if(c32 == false){
                    b32.setImageResource(R.drawable.bon2_opt);
                    c32 = true;
                }
                else{
                    b32.setImageResource(R.drawable.boff2_opt);
                    c32 = false;
                }
            }
        });

        b33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("011");
                if(c33 == false){
                    b33.setImageResource(R.drawable.bon2_opt);
                    c33 = true;
                }
                else{
                    b33.setImageResource(R.drawable.boff2_opt);
                    c33 = false;
                }
            }
        });

        b34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("012");
                if(c34 == false){
                    b34.setImageResource(R.drawable.bon2_opt);
                    c34 = true;
                }
                else{
                    b34.setImageResource(R.drawable.boff2_opt);
                    c34 = false;
                }
            }
        });

        b41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("013");
                if(c41 == false){
                    b41.setImageResource(R.drawable.bon2_opt);
                    c41 = true;
                }
                else{
                    b41.setImageResource(R.drawable.boff2_opt);
                    c41 = false;
                }
            }
        });

        b42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("014");
                if(c42 == false){
                    b42.setImageResource(R.drawable.bon2_opt);
                    c42 = true;
                }
                else{
                    b42.setImageResource(R.drawable.boff2_opt);
                    c42 = false;
                }
            }
        });

        b43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("015");
                if(c43 == false){
                    b43.setImageResource(R.drawable.bon2_opt);
                    c43 = true;
                }
                else{
                    b43.setImageResource(R.drawable.boff2_opt);
                    c43 = false;
                }
            }
        });

        b44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("016");
                if(c44 == false){
                    b44.setImageResource(R.drawable.bon2_opt);
                    c44 = true;
                }
                else{
                    b44.setImageResource(R.drawable.boff2_opt);
                    c44 = false;
                }
            }
        });

        b51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("017");
                if(c51 == false){
                    b51.setImageResource(R.drawable.bon2_opt);
                    c51 = true;
                }
                else{
                    b51.setImageResource(R.drawable.boff2_opt);
                    c51 = false;
                }
            }
        });

        b52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("018");
                if(c52 == false){
                    b52.setImageResource(R.drawable.bon2_opt);
                    c52 = true;
                }
                else{
                    b52.setImageResource(R.drawable.boff2_opt);
                    c52 = false;
                }
            }
        });

        b53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("019");
                if(c53 == false){
                    b53.setImageResource(R.drawable.bon2_opt);
                    c53 = true;
                }
                else{
                    b53.setImageResource(R.drawable.boff2_opt);
                    c53 = false;
                }
            }
        });

        b54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("020");
                if(c54 == false){
                    b54.setImageResource(R.drawable.bon2_opt);
                    c54 = true;
                }
                else{
                    b54.setImageResource(R.drawable.boff2_opt);
                    c54 = false;
                }
            }
        });

        b61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("021");
                if(c61 == false){
                    b61.setImageResource(R.drawable.bon2_opt);
                    c61 = true;
                }
                else{
                    b61.setImageResource(R.drawable.boff2_opt);
                    c61 = false;
                }
            }
        });

        b62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("022");
                if(c62 == false){
                    b62.setImageResource(R.drawable.bon2_opt);
                    c62 = true;
                }
                else{
                    b62.setImageResource(R.drawable.boff2_opt);
                    c62 = false;
                }
            }
        });

        b63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("023");
                if(c63 == false){
                    b63.setImageResource(R.drawable.bon2_opt);
                    c63 = true;
                }
                else{
                    b63.setImageResource(R.drawable.boff2_opt);
                    c63 = false;
                }
            }
        });

        b64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("024");
                if(c64 == false){
                    b64.setImageResource(R.drawable.bon2_opt);
                    c64 = true;
                }
                else{
                    b64.setImageResource(R.drawable.boff2_opt);
                    c64 = false;
                }
            }
        });

        b71.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("025");
                if(c71 == false){
                    b71.setImageResource(R.drawable.bon2_opt);
                    c71 = true;
                }
                else{
                    b71.setImageResource(R.drawable.boff2_opt);
                    c71 = false;
                }
            }
        });

        b72.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("026");
                if(c72 == false){
                    b72.setImageResource(R.drawable.bon2_opt);
                    c72 = true;
                }
                else{
                    b72.setImageResource(R.drawable.boff2_opt);
                    c72 = false;
                }
            }
        });

        b73.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("027");
                if(c73 == false){
                    b73.setImageResource(R.drawable.bon2_opt);
                    c73 = true;
                }
                else{
                    b73.setImageResource(R.drawable.boff2_opt);
                    c73 = false;
                }
            }
        });

        b74.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("028");
                if(c74 == false){
                    b74.setImageResource(R.drawable.bon2_opt);
                    c74 = true;
                }
                else{
                    b74.setImageResource(R.drawable.boff2_opt);
                    c74 = false;
                }
            }
        });

        b81.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("029");
                if(c81 == false){
                    b81.setImageResource(R.drawable.bon2_opt);
                    c81 = true;
                }
                else{
                    b81.setImageResource(R.drawable.boff2_opt);
                    c81 = false;
                }
            }
        });

        b82.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("030");
                if(c82 == false){
                    b82.setImageResource(R.drawable.bon2_opt);
                    c82 = true;
                }
                else{
                    b82.setImageResource(R.drawable.boff2_opt);
                    c82 = false;
                }
            }
        });

        b83.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("031");
                if(c83 == false){
                    b83.setImageResource(R.drawable.bon2_opt);
                    c83 = true;
                }
                else{
                    b83.setImageResource(R.drawable.boff2_opt);
                    c83 = false;
                }
            }
        });

        b84.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("032");
                if(c84 == false){
                    b84.setImageResource(R.drawable.bon2_opt);
                    c84 = true;
                }
                else{
                    b84.setImageResource(R.drawable.boff2_opt);
                    c84 = false;
                }
            }
        });

        b91.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("033");
                if(c91 == false){
                    b91.setImageResource(R.drawable.bon2_opt);
                    c91 = true;
                }
                else{
                    b91.setImageResource(R.drawable.boff2_opt);
                    c91 = false;
                }
            }
        });

        b92.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("034");
                if(c92 == false){
                    b92.setImageResource(R.drawable.bon2_opt);
                    c92 = true;
                }
                else{
                    b92.setImageResource(R.drawable.boff2_opt);
                    c92 = false;
                }
            }
        });

        b93.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("035");
                if(c93 == false){
                    b93.setImageResource(R.drawable.bon2_opt);
                    c93 = true;
                }
                else{
                    b93.setImageResource(R.drawable.boff2_opt);
                    c93 = false;
                }
            }
        });

        b94.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("036");
                if(c94 == false){
                    b94.setImageResource(R.drawable.bon2_opt);
                    c94 = true;
                }
                else{
                    b94.setImageResource(R.drawable.boff2_opt);
                    c94 = false;
                }
            }
        });

        b101.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("037");
                if(c101 == false){
                    b101.setImageResource(R.drawable.bon2_opt);
                    c101 = true;
                }
                else{
                    b101.setImageResource(R.drawable.boff2_opt);
                    c101 = false;
                }
            }
        });

        b102.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("038");
                if(c102 == false){
                    b102.setImageResource(R.drawable.bon2_opt);
                    c102 = true;
                }
                else{
                    b102.setImageResource(R.drawable.boff2_opt);
                    c102 = false;
                }
            }
        });

        b103.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("039");
                if(c103 == false){
                    b103.setImageResource(R.drawable.bon2_opt);
                    c103 = true;
                }
                else{
                    b103.setImageResource(R.drawable.boff2_opt);
                    c103 = false;
                }
            }
        });

        b104.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("040");
                if(c104 == false){
                    b104.setImageResource(R.drawable.bon2_opt);
                    c104 = true;
                }
                else{
                    b104.setImageResource(R.drawable.boff2_opt);
                    c104 = false;
                }
            }
        });

        b111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("041");
                if(c111 == false){
                    b111.setImageResource(R.drawable.bon2_opt);
                    c111 = true;
                }
                else{
                    b111.setImageResource(R.drawable.boff2_opt);
                    c111 = false;
                }
            }
        });

        b112.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("042");
                if(c112 == false){
                    b112.setImageResource(R.drawable.bon2_opt);
                    c112 = true;
                }
                else{
                    b112.setImageResource(R.drawable.boff2_opt);
                    c112 = false;
                }
            }
        });

        b113.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("043");
                if(c113 == false){
                    b113.setImageResource(R.drawable.bon2_opt);
                    c113 = true;
                }
                else{
                    b113.setImageResource(R.drawable.boff2_opt);
                    c113 = false;
                }
            }
        });

        b114.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("044");
                if(c114 == false){
                    b114.setImageResource(R.drawable.bon2_opt);
                    c114 = true;
                }
                else{
                    b114.setImageResource(R.drawable.boff2_opt);
                    c114 = false;
                }
            }
        });

        b121.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("045");
                if(c121 == false){
                    b121.setImageResource(R.drawable.bon2_opt);
                    c121 = true;
                }
                else{
                    b121.setImageResource(R.drawable.boff2_opt);
                    c121 = false;
                }
            }
        });

        b122.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("046");
                if(c122 == false){
                    b122.setImageResource(R.drawable.bon2_opt);
                    c122 = true;
                }
                else{
                    b122.setImageResource(R.drawable.boff2_opt);
                    c122 = false;
                }
            }
        });

        b123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("047");
                if(c123 == false){
                    b123.setImageResource(R.drawable.bon2_opt);
                    c123 = true;
                }
                else{
                    b123.setImageResource(R.drawable.boff2_opt);
                    c123 = false;
                }
            }
        });

        b124.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("048");
                if(c124 == false){
                    b124.setImageResource(R.drawable.bon2_opt);
                    c124 = true;
                }
                else{
                    b124.setImageResource(R.drawable.boff2_opt);
                    c124 = false;
                }
            }
        });

        b131.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("049");
                if(c131 == false){
                    b131.setImageResource(R.drawable.bon2_opt);
                    c131 = true;
                }
                else{
                    b131.setImageResource(R.drawable.boff2_opt);
                    c131 = false;
                }
            }
        });

        b132.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("050");
                if(c132 == false){
                    b132.setImageResource(R.drawable.bon2_opt);
                    c132 = true;
                }
                else{
                    b132.setImageResource(R.drawable.boff2_opt);
                    c132 = false;
                }
            }
        });

        b133.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("051");
                if(c133 == false){
                    b133.setImageResource(R.drawable.bon2_opt);
                    c133 = true;
                }
                else{
                    b133.setImageResource(R.drawable.boff2_opt);
                    c133 = false;
                }
            }
        });

        b134.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("052");
                if(c134 == false){
                    b134.setImageResource(R.drawable.bon2_opt);
                    c134 = true;
                }
                else{
                    b134.setImageResource(R.drawable.boff2_opt);
                    c134 = false;
                }
            }
        });

        b141.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("053");
                if(c141 == false){
                    b141.setImageResource(R.drawable.bon2_opt);
                    c141 = true;
                }
                else{
                    b141.setImageResource(R.drawable.boff2_opt);
                    c141 = false;
                }
            }
        });

        b142.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("054");
                if(c142 == false){
                    b142.setImageResource(R.drawable.bon2_opt);
                    c142 = true;
                }
                else{
                    b142.setImageResource(R.drawable.boff2_opt);
                    c142 = false;
                }
            }
        });

        b143.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("055");
                if(c143 == false){
                    b143.setImageResource(R.drawable.bon2_opt);
                    c143 = true;
                }
                else{
                    b143.setImageResource(R.drawable.boff2_opt);
                    c143 = false;
                }
            }
        });

        b144.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("056");
                if(c144 == false){
                    b144.setImageResource(R.drawable.bon2_opt);
                    c144 = true;
                }
                else{
                    b144.setImageResource(R.drawable.boff2_opt);
                    c144 = false;
                }
            }
        });

    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException
    {
//crea un conex  ion de salida segura para el dispositivo
        //usando el servicio UUID
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //Consigue la direccion MAC desde DeviceListActivity via intent
        Intent intent = getIntent();
        //Consigue la direccion MAC desde DeviceListActivity via EXTRA
        address = intent.getStringExtra(DispositivosBT.EXTRA_DEVICE_ADDRESS);//<-<- PARTE A MODIFICAR >->->
        //Setea la direccion MAC
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try
        {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
        }
        // Establece la conexión con el socket Bluetooth.
        try
        {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {}
        }
        MyConexionBT = new ConnectedThread(btSocket);
        MyConexionBT.start();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        try
        { // Cuando se sale de la aplicación esta parte permite
            // que no se deje abierto el socket
            btSocket.close();
        } catch (IOException e2) {}
    }

    //Comprueba que el dispositivo Bluetooth Bluetooth está disponible y solicita que se active si está desactivado
    private void VerificarEstadoBT() {

        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), "El dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    //Crea la clase que permite crear el evento de conexion
    private class ConnectedThread extends Thread
    {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket)
        {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try
            {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run()
        {
            byte[] buffer = new byte[256];
            int bytes;

            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
        //Envio de trama
        public void write(String input)
        {
            try {
                mmOutStream.write(input.getBytes());
            }
            catch (IOException e)
            {
                //si no es posible enviar datos se cierra la conexión
                Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
