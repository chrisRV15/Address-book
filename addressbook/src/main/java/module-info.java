module address.book {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires transitive javafx.graphics;
    requires java.base;
    requires javafx.base;

    opens address.book to javafx.fxml;
    exports address.book;
}
