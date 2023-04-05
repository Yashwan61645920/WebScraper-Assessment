import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScrapper {
    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("https://www.staples.com/Computer-office-desks/cat_CL210795/663ea?icid=BTS:2020:STUDYSPACE:DESKS").get();
            //System.out.println(document.outerHtml());
            Elements products = document.select(".SearchResults-UX2__searchGridColumn");

            List<String[]> productDetails = new ArrayList<>();
            for (Element product : products) {
                String name = product.select(".standard-tile__title ").text();
                String price = product.select(".standard-tile__price_wrapper ").text();
                String sku = product.select(".item_number").text();
                String model = product.select(".product-model").text();
                String category = product.select("h1.searchTerm").text();
                String description = product.select(".product-description").text();

                String[] details = { name, price, sku, model, category, description };

                productDetails.add(details);
            }
            FileWriter csvWriter = new FileWriter("product_details.csv");
            csvWriter.append("Product Name,Product Price,Item Number/SKU/Product Code,Model Number,Product Category,Product Description\n");
            for (String[] details : productDetails) {
                csvWriter.append(String.join(",", details));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();

            System.out.println("Product details exported to CSV file successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
