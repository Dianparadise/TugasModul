module com.main.kegiatan_modul6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.main.kegiatan_modul6 to javafx.fxml;
    exports com.main.kegiatan_modul6;
    exports data;
    opens data to javafx.fxml;
    exports books;
    opens books to javafx.fxml;
    exports util;
    opens util to javafx.fxml;
    exports exception;
    opens exception to javafx.fxml;
}