package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class VarukorgItem extends AnchorPane {

    ShoppingItem shoppingItem;
    Model model;

    @FXML
    Label labelVarukorgVara;
    @FXML
    TextField labelVarukorgAntal;
    @FXML
    Label labelVarukorgPris;

    public VarukorgItem(ShoppingItem shoppingItem, Model model){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("varukorgitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.model = model;
        this.shoppingItem = shoppingItem;
        updateThisItem();
    }

    public void updateThisItem(){
        labelVarukorgAntal.setText(Integer.toString( (int) shoppingItem.getAmount() ));
        labelVarukorgVara.setText(shoppingItem.getProduct().getName());
        labelVarukorgPris.setText(Double.toString(shoppingItem.getTotal() ) + "kr");
    }

    @FXML
    public void trashObjectButton(){
        model.removeFromShoppingCart(shoppingItem.getProduct());

    }

    @FXML
    public void plusButton(){
        model.updateShoppingCart(shoppingItem.getProduct(), 1);
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
    }

    @FXML
    public void minusButton(){
        model.updateShoppingCart(shoppingItem.getProduct(), -1);
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, false);
        if ((int) shoppingItem.getAmount() == 0){
            trashObjectButton();
        }

    }



}
