package org.example;

import com.jcraft.jsch.*;

import java.io.*;

/**
 * Gilberto
 * Tarea2 UT5
 * P.Servicos y Procesos
 */
public class ClienteSSH {

    public static void main( String[] args ) {

        //Configuracion para conectarse al servidor
    String host = "test.rebex.net";  //Direccion del servidor
    String user = "demo"; //Nombre del usuario
    String password = "password";  //Contraseña para acceder

    try{

        //PASO 1 Inicializar JSCH y establecer sesion SSH
        JSch jsch = new JSch();

        //Segun esas librerias (dependencias) hemos de crear una sesion SSH con usuario, hosto y puerto
        Session session = jsch.getSession(user, host, 22);

        //Establecemos la contraseña para autenticarse
        session.setPassword(password);

        //Desactivar la verificacion estricta de la clave del host
        //NOTA: En produccion esto deberia estar activado por seguridad "si"
        session.setConfig("StrictHostKeyChecking", "no");

        //Hacemos conexion
        session.connect();
        System.out.println("Conectado con éxito a " + "servidor_ssh: " + host);


      /*  ChannelExec channel =(ChannelExec) session.openChannel("exec");

        //Definidos el comando que se ejecutara en el servidor remoto
        channel.setCommand("ls -l");

        //No enviaremos datos de entrada al comando
        channel.setInputStream(null);

        //Los errores del servidor se mostraran en nuestra consola de errores
        channel.setErrStream(System.err);

        //Leer la salida del comando
        InputStream input = channel.getInputStream(); */

       // Abrir canal tipo shell (terminal interactiva)
        ChannelShell channel = (ChannelShell) session.openChannel("shell");

        InputStream input = channel.getInputStream();
        OutputStream output = channel.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        //Conectamos el canal
        channel.connect();
        System.out.println("Comando ejecutado.");


        //Leer la salida byte a byte y mostrarla en la consola
        //read() devuelve -1 cuando no hay mas datos que leer
     /*   int data;
        while ((data = input.read()) != -1) {
            //Convertimos el byte a caracter e imprimimos
            System.out.print((char) data);
        }*/

        //Bucle para poder escribir los comandos que hagan falta
       String comando;
        while (true) {
            System.out.print("ssh");
            comando = teclado.readLine();

            if (comando.equalsIgnoreCase("exit")) {
                break;
            }

            // Enviamos comando al servidor
            writer.write(comando);
            writer.newLine();
            writer.flush();

            // Pequeña pausa para recibir la respuesta
            Thread.sleep(500);

            // Leemos respuesta disponible
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
        }

        //Cerrar la conexion
        channel.disconnect();
        session.disconnect();
        System.out.println("Se ha cerrado la conexion correctamente.");

    }catch(JSchException e){
        e.printStackTrace();
    }catch (Exception e) {
        e.printStackTrace();
    }


    }
}
