package net.martinburger.sesqa.programming.codeopolis.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.martinburger.sesqa.programming.codeopolis.domainmodel.CityState;

import java.io.*;

public class JSONService<T> {
    private Gson gson;
    private Class<T> genericObject;

    private JSONService(){
        this.gson = new GsonBuilder().create();
    }

    public static JSONService<CityState> cityStateJSONService() {
        return new JSONService<CityState>();
    }

    public void write(T data, String filename) {
        try(FileWriter fw = new FileWriter(filename); BufferedWriter bufferedWriter = new BufferedWriter(fw)) {
            String dataJson = this.gson.toJson(data);

            bufferedWriter.write(dataJson);
        } catch (FileNotFoundException e) {
            System.err.println("Error when opening / creating file for writing the city state.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
            System.exit(1);
        }
    }

    public T read(String filename, T type) {
        T jsonObject = null;

        try(FileReader fr = new FileReader(filename); BufferedReader bufferedReader = new BufferedReader(fr)) {

            jsonObject = (T) this.gson.fromJson(bufferedReader, type.getClass());
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
            System.exit(1);
        } catch(IOException e) {
            System.err.println("IO Error: " + e.getMessage());
            System.exit(1);
        }

        return jsonObject;
    }
}
