package com.script;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class App 
{
    static ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
    public static void main( String[] args )
    {
        try {
            ServerSocket servsock= new ServerSocket(3000);
            XmlMapper mapper = new XmlMapper();
            boolean loop = true;
            Classroom c1 = new Classroom(5, "DIA", "Itis Meucci");
            Classroom c2 = new Classroom(4, "BIA", "Itis Meucci");
            Student s1 = new Student("Alessio", "Didilescu", 19);
            Student s2 = new Student("Alessandro","Gonzalez", 20);
            Student s3 = new Student("Matteo", "Falli", 19);
            Student s4 = new Student("Anatolie", "Pavlov", 21);
            Student s5 = new Student("Singh", "Swarang", 20);
            Student s6 = new Student("Ryan", "Mohd", 19);
            c1.addStudent(s2);
            c1.addStudent(s4);
            c1.addStudent(s5);
            c2.addStudent(s1);
            c2.addStudent(s3);
            c2.addStudent(s6);
            classrooms.add(c1);
            classrooms.add(c2);
            while(loop){
                System.out.println("The server is waiting");
                Socket socket = servsock.accept();
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                System.out.println("A new client is connected");
                for(Classroom c : App.classrooms){
                    output.writeBytes(mapper.writeValueAsString(c) + "\n");
                }
            }
            servsock.close();
            System.out.println("Server shutdown");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
