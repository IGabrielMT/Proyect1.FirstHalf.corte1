package co.edu.uptc.presenter;

import File_Txt1.src.co.edu.uptc.servicesfiles.ProcessesFiles;
import co.edu.uptc.model.CombineNames;
import co.edu.uptc.text.ManagerProperties;
import co.edu.uptc.view.View;

import java.io.File;
import java.util.List;

public class Presenter {
    private final CombineNames combineNames;
    private final ProcessesFiles process;
    private final ManagerProperties managerProperties;
    private final View view = new View();
    public Presenter(){
        combineNames = new CombineNames();
        process = new ProcessesFiles();
        managerProperties = new ManagerProperties();
        managerProperties.setFileName("config.properties");

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
                    String filePath = managerProperties.getValue("filename_names");
                    showList(filePath);
                    break;
                case 4:
                    String filePath2 = managerProperties.getValue("filename_lastnames");
                    showList(filePath2);
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
        System.out.println(filePath);
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
        String nameValue = managerProperties.getValue("filename_names");
        process.setNameFile(nameValue);
        try {
            combineNames.setNames(process.extraerString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String lastNameValue = managerProperties.getValue("filename_lastnames");
        process.setNameFile(lastNameValue);
        try {
            combineNames.setLastNames(process.extraerString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
