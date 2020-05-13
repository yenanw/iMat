package sample;

import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

import java.util.List;

public class Model {

    //Den enda instansen av modelklassen
    private static Model model;
    //referens till DataHandlerns som lagrar all information om programmet
    private IMatDataHandler iMatDataHandler;

    private ProductHandler productHandler;

    //privat default konstruktor för singleton pattern
    private Model(){}

    //metod för att se till att det endast finns en instans av modell
    public static Model getInstance (){
        if (model==null){
            model=new Model();
            model.init();
        }
        return model;

    }
    //skapar referens till IMatDataHandler
    private void init() {

        iMatDataHandler = IMatDataHandler.getInstance();
        productHandler = ProductHandler.getInstance();
    }
    //Alla dessa under är i princip setters och getters
    public List<ProductA> getProducts() {
        return productHandler.getProducts();
    }

    public ProductA getProduct(int idNbr) {
        return productHandler.getProduct(idNbr);
    }

    public List<ProductA> findProducts(String s) {
        return productHandler.getProductAList(iMatDataHandler.findProducts(s));
    }

    // dont need ProductA here because ProductA extends Product
    public Image getImage(Product p) {
        return iMatDataHandler.getFXImage(p);
    }

    public Image getImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
    }

    public void addToShoppingCart(Product p) {
        ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();

        ShoppingItem item = new ShoppingItem(p);
        Model.getInstance().getShoppingCart().addItem(item);

        //shoppingCart.addProduct(p);
    }



    public CreditCard getCreditCard() {
        return iMatDataHandler.getCreditCard();
    }

    public Customer getCustomer() {
        return iMatDataHandler.getCustomer();
    }

    public ShoppingCart getShoppingCart() {
        return iMatDataHandler.getShoppingCart();
    }

    public void clearShoppingCart() {

        iMatDataHandler.getShoppingCart().clear();

    }

    public void placeOrder() {

        iMatDataHandler.placeOrder();

    }


    public int getNumberOfOrders() {

        return iMatDataHandler.getOrders().size();


    }
    public List <Order> getOrders(){
        return iMatDataHandler.getOrders();


    }

    public void shutDown() {
        iMatDataHandler.shutDown();
    }

    public void addFavorite(Product p){
        iMatDataHandler.addFavorite(p);

    }

    public List<ProductA> getFavorites() {
        return productHandler.getProductAList(iMatDataHandler.favorites());
    }
}