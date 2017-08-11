package edu.agregator.ejb;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.*;
import java.util.List;


//code4craft
/**
 * Created by 1 on 09.07.2017.
 */
@TargetUrl("https://www.citilink.ru/catalog/computers_and_notebooks/hdd/\\w+/\\d+")
@HelpUrl("https://www.citilink.ru/catalog/computers_and_notebooks/hdd/\\w+")

public class GithubRepoPageProcessor implements PageProcessor {
    //public DataSource dataSource;
    public  List<String> names, k;
    public  List<String> price;
    public  List<String> disc;
//    @ExtractBy(value = "//h1[@class='public']/strong/a/text()", notNull = true)
//    private String name;
//
//    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
//    private String author;
//
//    @ExtractBy("//div[@id='readme']/tidyText()")
//    private String readme;

    // Часть I: сканирование конфигурации сайта, включая кодирование, пространство искателя, повторы и т. Д.
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // Обработка пользовательских интерфейсов ядра логического искателя, где подготовка логики извлечения
    public void process(Page page) {
        // Часть II: определение того, как извлечь информацию о странице, и сохранить
        page.addTargetRequests(page.getHtml().links().regex("(https://citilink.ru/catalog/computers_and_notebooks/hdd/\\w+/\\d+)").all());
        //page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//a[@class='link_gtm-js link_pageevents-js ddl_product_link']/text()").toString());

        if (page.getResultItems().get("name")==null){
            // пропустить эту страницу
            page.setSkip(true);
        }

        page.putField("readme", page.getHtml().xpath("//span[@class='h3 ']/tidyText()"));
        // Часть III: с последующего URL-адреса страницы поиска для искателя
        page.addTargetRequests(page.getHtml().links().regex("(https://www.citilink.ru/catalog/computers_and_notebooks/[\\w\\-]+/[\\w\\-]+)").all());
        //page.addTargetRequests(page.getHtml().links().regex("(https://www.citilink.ru/catalog/computers_and_notebooks/hdd/ssd_in/\\?p\\=[\\d\\-]+)").all());

        //page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        //getHtml().css("div.pagination").links().regex(".*\\/search\\/\\?l=java.*").all();

        names = page.getHtml().xpath("//span/a[@class='link_gtm-js link_pageevents-js ddl_product_link']/text()").all();
        price = page.getHtml().xpath("//span[@class='subcategory-product-item__price subcategory-product-item__price_standart']/ins[1]/text()").all();
        //List<String> feedback = page.getHtml().xpath("//a[@class='link_gtm-js link_pageevents-js js']/text()").all();
        disc = page.getHtml().xpath("//p[@class='short_description']/text()").all();

        page.addTargetRequests(names);
        //System.out.println(names);
        //
        //ll

        try(FileWriter writer = new FileWriter("C:\\Users\\1\\Documents\\names.txt",true)) {
            writer.write(String.valueOf(names).substring(1,String.valueOf(names).length()-1)+",");
            writer.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        try(FileWriter writer = new FileWriter("C:\\Users\\1\\Documents\\price.txt",true)) {
            writer.write(String.valueOf(price).substring(1,String.valueOf(price).length()-1)+",");
            writer.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        try(FileWriter writer = new FileWriter("C:\\Users\\1\\Documents\\disc.txt",true)) {
            writer.write(String.valueOf(disc).substring(1,String.valueOf(disc).length()-1)+",");//      ,       #       "+disc.size()+"    #
            writer.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        //System.out.println(price);

        // set to request attribute.
        //request.setAttribute("departments", names);

    }



    @Override
    public Site getSite() {
        return site;
    }

//        public static void main(String[] args) {
//            Spider.create(new GithubRepoPageProcessor())
//                    // От "https://github.com/code4craft" начали понимать
//                    .addUrl("https://github.com/code4craft")
//                    // Открываем 5 потоков Crawler
//                    .thread(5)
//                    // Запуск сканера
//                    .run();
//
//            Spider.create(new GithubRepoPageProcessor())
//                    // From "https://github.com/code4craft" began to grasp
//                    .addUrl("https://github.com/code4craft")
//                    .addPipeline(new JsonFilePipeline("C:\\webmagic\\"))
//                    // Open 5 threads of Crawl
//                    .thread(5)
//                    // Start Crawl
//                    .run();
//
//        }
}

