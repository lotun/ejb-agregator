package edu.agregator.ejb;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


//code4craft

/**
 * Created by 1 on 09.07.2017.
 *///https://www.dns-shop.ru/search/?q=ssd
@TargetUrl("https://just.ru/komplektuyuschie/hdd/\\w+")
@HelpUrl("https://just.ru/komplektuyuschie/hdd/\\w+")
//@TargetUrl("https://www.dns-shop.ru/catalog/8a9ddfba20724e77/ssd-nakopiteli/\\w+/\\d+")
//@HelpUrl("https://www.dns-shop.ru/catalog/8a9ddfba20724e77/ssd-nakopiteli/\\w+")

public class JastRepoPageProcessor implements PageProcessor {
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
        page.addTargetRequests(page.getHtml().links().regex("(https://just.ru/komplektuyuschie/hdd/ssd/)").all());
        //page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        //page.putField("name", page.getHtml().xpath("//a[@class='link_gtm-js link_pageevents-js ddl_product_link']/text()").toString());

        if (page.getResultItems().get("name")==null){
            // пропустить эту страницу
            page.setSkip(true);
        }

        page.putField("readme", page.getHtml().xpath("//span[@class='h3 ']/tidyText()"));
        // Часть III: с последующего URL-адреса страницы поиска для искателя
        page.addTargetRequests(page.getHtml().links().regex("(https://just.ru/komplektuyuschie/hdd/ssd/[\\w\\-]+)").all());
        //page.addTargetRequests(page.getHtml().links().regex("(https://www.dns-shop.ru/catalog/8a9ddfba20724e77/ssd-nakopiteli/?p=3&i=1)").all());
        //page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        //getHtml().css("div.pagination").links().regex(".*\\/search\\/\\?l=java.*").all();

        names = page.getHtml().xpath("//div[@class='description']/a[1]/text()").all();
        price = page.getHtml().xpath("//p[@class='price']/span[1]/text()").all();//p[@class='price']/span[@class='price-abbr']
        //List<String> feedback = page.getHtml().xpath("//a[@class='link_gtm-js link_pageevents-js js']/text()").all();
        disc = page.getHtml().xpath("//div[@class='description']/p[1]/text()").all();

        page.addTargetRequests(names);
        System.out.println(names);
        //
        //ll

        try(FileWriter writer = new FileWriter("C:\\Users\\1\\Documents\\namesD.txt",true)) {
            writer.write(String.valueOf(names).substring(1,String.valueOf(names).length()-1)+",");
            writer.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        try(FileWriter writer = new FileWriter("C:\\Users\\1\\Documents\\priceD.txt",true)) {
            writer.write(String.valueOf(price).substring(1,String.valueOf(price).length()-1)+",");
            writer.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        try(FileWriter writer = new FileWriter("C:\\Users\\1\\Documents\\discD.txt",true)) {
            writer.write(String.valueOf(disc).substring(1,String.valueOf(disc).length()-1)+",");
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

