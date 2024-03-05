package co.edu.uptc.presenter;

import File_Txt1.src.co.edu.uptc.servicesfiles.ProcessesFiles;
import co.edu.uptc.model.CombineNames;
import co.edu.uptc.view.View;

import java.io.File;
import java.util.List;

public class Presenter {
    private final CombineNames combineNames = new CombineNames();
    private final ProcessesFiles process = new ProcessesFiles();
    private final View view = new View();
    public Presenter(){

    }
    public void menu(){
        int option;
        do {
            view.menu(new String[]{"Combinar nombres", "Modificar el Archivo Generado", "Ver Lista de Nombres", "Ver Lista de Apellidos", "Salir"});
            option = Integer.parseInt(view.askInfo("Ingrese una opción (numero)"));
            switch (option) {
                case 1:
                    askInfoForCombineNames();
                    break;
                case 2:
                    modifyFile();
                    break;
                case 3:
                    showList("data/Names.txt");
                    break;
                case 4:
                    showList("data/LastNames.txt");
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    view.showMessage("Opción no valida");
                    break;
            }
        }while (true);

    }
    private void showList(String filePath) {
        process.setNameFile(filePath);
        try {
            view.showMessage(process.extraerString().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modifyFile() {
        if (doesFileExist("Combinaciones")){
            modifyFileMenu();
        }else{
            view.showMessage("El archivo no existe");
            askInfoForCombineNames();
        }
    }
    private void modifyFileMenu(){
        int option;
        do{
            view.menu(new String[]{"Todo en mayusculas", "Todo en minusculas", "Primera letra en mayuscula", "Salir"});
            option = Integer.parseInt(view.askInfo("Ingrese una opción (numero)"));
            switch (option){
                case 1:
                    allInUpperCase();
                    view.showMessage("Archivo modificado");
                    break;
                case 2:
                    allInLowerCase();
                    view.showMessage("Archivo modificado");
                    break;
                case 3:
                    firstLetterInUpperCase();
                    view.showMessage("Archivo modificado");
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    view.showMessage("Opción no valida");
                    break;
            }
        }while (true);
    }

    private void firstLetterInUpperCase() {
        List<String> list = null;
        try {
            process.setNameFile("Combinaciones");
            list = process.extraerString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert list != null;
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).substring(0,1).toUpperCase() + list.get(i).substring(1).toLowerCase());
        }
        process.crearArchivoDesdeList(list, "Combinaciones", "");
    }

    private void allInLowerCase() {
        List<String> list = null;
        try {
            process.setNameFile("Combinaciones");
            list = process.extraerString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert list != null;
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).toLowerCase());
        }
        process.crearArchivoDesdeList(list, "Combinaciones", " ");

    }

    private void allInUpperCase() {
        List<String> list = null;
        try {
            process.setNameFile("Combinaciones");
            list = process.extraerString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert list != null;
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).toUpperCase());
        }
        process.crearArchivoDesdeList(list, "Combinaciones", " ");
    }

    public boolean doesFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }


    private void askInfoForCombineNames() {
        setInfoForCombineNames();
        int namesQuantity = Integer.parseInt(view.askInfo("Ingrese la cantidad de nombres"));
        int lastNamesQuantity = Integer.parseInt(view.askInfo("Ingrese la cantidad de apellidos"));
        try {
            process.crearArchivoDesdeList(combineNames.generateCombinations(namesQuantity, lastNamesQuantity), "Combinaciones", view.askInfo("Ingrese el separador"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        view.showMessage("Archivo creado con nombre Combinaciones.txt en la carpeta raiz del proyecto");
    }
    private void setInfoForCombineNames(){
        process.setNameFile("data/Names.txt");
        try {
            combineNames.setNames(process.extraerString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        process.setNameFile("data/LastNames.txt");
        try {
            combineNames.setLastNames(process.extraerString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
