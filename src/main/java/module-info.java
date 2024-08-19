module org.vazquezj.criterios.criteriosdecisiones {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.vazquezj.criterios.criteriosdecisiones to javafx.fxml;
    exports org.vazquezj.criterios.criteriosdecisiones;
}