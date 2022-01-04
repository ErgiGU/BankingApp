package pleasefivebank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pleasefivebank.UserPage.*;
import java.io.IOException;

public class Main extends Application {

    public static Stage mainWindow = null;

    @Override
    public void start(Stage stage) throws IOException {
        mainWindow = stage;
        stage.setTitle("P5B");
        showPage("Entry-Page.fxml");
        stage.show();
    }


    public static void main(String[] args) {

        try {
            Mongo.mongo();//Mongo is a utility class and cannot be instantiated
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch(args);
    }

    public static void showPage(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load());
        mainWindow.setScene(scene);
    }
    //lotti and juan
    public static void showLoginPage(String fullName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserHomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HomePageController homePageController = fxmlLoader.getController();
        homePageController.setName(fullName);
        mainWindow.setScene(scene);
    }
    //Juan
    public static void showCardsPage(String fullName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CardsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        CardsController cardsController = fxmlLoader.getController();
        cardsController.setName(fullName);
        mainWindow.setScene(scene);
    }
    //Juan and Lotti
    public static void showLoansPage(String fullName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("StudentLoans.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LoansController loansController = fxmlLoader.getController();
        loansController.setName(fullName);
        mainWindow.setScene(scene);
    }
    //Juan
    public static void showTransactionsPage(String fullName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Transactions.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        TransactionsController transactionsController = fxmlLoader.getController();
        transactionsController.setName(fullName);
        mainWindow.setScene(scene);
    }
    //Juan
    public static void showRequestTransactionsPage(String fullName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("requestTransactionPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        RequestMoneyController requestMoneyController = fxmlLoader.getController();
        requestMoneyController.setName(fullName);
        mainWindow.setScene(scene);
    }
    //Juan
    public static void showTransferMoneyPage(String fullName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("makeTransactionPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MakeTransferController transferMoneyController = fxmlLoader.getController();
        transferMoneyController.setName(fullName);
        mainWindow.setScene(scene);
    }
    //Juan
    public static void showEditDetailsPage(String fullName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EditAccountDetails.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        EditDetailsController editDetailsController = fxmlLoader.getController();
        editDetailsController.setData(fullName);
        mainWindow.setScene(scene);
    }
    //Juan
    public static void showNotificationsPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EditAccountDetails.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        NotificationsController notificationsController = fxmlLoader.getController();
        notificationsController.setData();
        mainWindow.setScene(scene);
    }



}