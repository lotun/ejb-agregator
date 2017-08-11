package edu.agregator.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Spider;

import java.io.*;
import java.util.*;
import java.util.concurrent.Future;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class AsyncEJB {
    public DataSource dataS=new DataSource();
    public DataSource dataJ=new DataSource();
    //объекты парсина
    GithubRepoPageProcessor parsDir = new GithubRepoPageProcessor();
    JastRepoPageProcessor parsDirD = new JastRepoPageProcessor();

    private static final Logger log = LoggerFactory.getLogger(AsyncEJB.class);

    @PostConstruct
    public void init() {
        log.info("AsyncEJB created");
    }

    @PreDestroy
    public void destroy() {
        log.info("AsyncEJB destroyed");
    }
    
    @Asynchronous
    public Future<String> sayHello() {
        //обнуляем файлы кэша
        //CloseFiles();

            log.debug("Start parsing method");

            //Drown();//разкоментировать для имитации парсинга

        //раскоментировать для реально парсинга
//        //Парсим ситилинк
//        Spider.create(parsDir)
//        //Spider.create(new GithubRepoPageProcessor())
//                // From "https://github.com/code4craft" began to grasp
//                .addUrl("https://www.citilink.ru/catalog/computers_and_notebooks/hdd/ssd_in")
//                //.addPipeline(new JsonFilePipeline("C:\\webmagic\\"))
//                // Open 5 threads of Crawl
//                .thread(1)
//                // Start Crawl
//                .run();
//        //парсим jast
//        Spider.create(parsDirD)
//
//                //Spider.create(new GithubRepoPageProcessor())
//                // From "https://github.com/code4craft" began to grasp
//                .addUrl("https://just.ru/komplektuyuschie/hdd/")
//                //.addPipeline(new JsonFilePipeline("C:\\webmagic\\"))
//                // Open 5 threads of Crawl
//                .thread(1)
//                // Start Crawl
//                .run();
//конец реального парсингаа


        log.debug("Return result");
        //переводим все данные с файлов в коллекции
        dataS.name=ReadDataSourseN("names.txt");
        dataS.price=ReadDataSourseP("price.txt");
        dataS.disc=ReadDataSourseD("disc.txt");
        dataJ.name=ReadDataSourseN("namesD.txt");
        dataJ.price=ReadDataSourseP("priceD.txt");
        dataJ.disc=ReadDataSourseD("discD.txt");

        return new AsyncResult<String>("Парсин завершен");

    }

    //обнуление файлов
    public void CloseFiles(){
        try(FileWriter writerN = new FileWriter("C:\\Users\\1\\Documents\\names.txt",false);
                     FileWriter writerP = new FileWriter("C:\\Users\\1\\Documents\\price.txt",false);
                     FileWriter writerD = new FileWriter("C:\\Users\\1\\Documents\\disc.txt",false);
                     FileWriter writerNd = new FileWriter("C:\\Users\\1\\Documents\\namesD.txt",false);
                     FileWriter writerPd = new FileWriter("C:\\Users\\1\\Documents\\priceD.txt",false);
                     FileWriter writerDd = new FileWriter("C:\\Users\\1\\Documents\\discD.txt",false);
        ) {
            writerN.write("");writerP.write("");writerD.write("");
            writerNd.write("");writerPd.write("");writerDd.write("");
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    //чтение имен
    public List<String> ReadDataSourseN(String name) {
        if (name==null) name="names.txt";
        List<String> list = null;

        try{
            FileInputStream fstream = new FileInputStream("C:\\Users\\1\\Documents\\"+name);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
           // String str[]=null;
                while ((strLine = br.readLine()) != null) {
                    //System.out.println(strLine);
                    String str[] = strLine.split(",");
                    //ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));
                    //list = Arrays.asList(str);
                    list = new ArrayList<String>(Arrays.asList(str));
                }

            if(name.equals("names.txt")){

                for (Iterator<String> iter = list.iterator();iter.hasNext();) {
                    String s = iter.next();

                    if (s.length()<15) {
                        iter.remove();
                    }
                }

            }
            return list;

        }catch (IOException e){
            System.out.println("Ошибка");
        }

        return list;
    }

//чтение цен

    public List<Long> ReadDataSourseP(String pri) {//List<String>
        if (pri==null) pri="price.txt";

        List<String> list = null;
        List<Long> listLong = new ArrayList<>();

        try{
            FileInputStream fstream = new FileInputStream("C:\\Users\\1\\Documents\\"+pri);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            // String str[]=null;
            //String strLine = "60 000, 5 200, 12 000";
            while ((strLine = br.readLine()) != null){

                strLine=strLine.replaceAll("\\s","");
                String str[] =strLine.split(",");
                list= Arrays.asList(str);
            }
            for (int i= 0;i<list.size();i++){
                listLong.add(Long.parseLong(list.get(i)));
            }

            return listLong;

        }catch (IOException e){
            listLong.add(23L);
            System.out.println("Ошибка");
        }

        return listLong;
    }
    //чтение описание
    public List<String> ReadDataSourseD(String dis) {
        if (dis==null) dis="disc.txt";

        List<String> list = null;

        try{
            FileInputStream fstream = new FileInputStream("C:\\Users\\1\\Documents\\"+dis);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            // String str[]=null;
            while ((strLine = br.readLine()) != null){
                String str[] =strLine.split(",");
                list= Arrays.asList(str);
            }

            return list;

        }catch (IOException e){
            System.out.println("Ошибка");
        }

        return list;
    }



    //заглушка--------------------------------------------------------------------
    public void Drown() {
        List<String> names = new ArrayList<>();
        List<String> price = new ArrayList<>();
        List<String> disc = new ArrayList<>();

        names.add("SSD накопитель 120ГБ");names.add("SSD накопитель 240ГБ");names.add("SSD накопитель 480ГБ");
        price.add("3 000");price.add("5 000");price.add("12 000");
        disc.add("скорость чтение 500");disc.add("скорость запси 500");disc.add("скорость интерфейса 6гб");

        try (FileWriter writer = new FileWriter("C:\\Users\\1\\Documents\\names.txt", true)) {
            writer.write(String.valueOf(names).substring(1, String.valueOf(names).length() - 1));
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try (FileWriter writer = new FileWriter("C:\\Users\\1\\Documents\\price.txt", true)) {
            writer.write(String.valueOf(price).substring(1, String.valueOf(price).length() - 1));
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try (FileWriter writer = new FileWriter("C:\\Users\\1\\Documents\\disc.txt", true)) {
            writer.write(String.valueOf(disc).substring(1, String.valueOf(disc).length() - 1));
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        names= new ArrayList<>();price= new ArrayList<>();disc= new ArrayList<>();
        names.add("Тверотельный накопитель SSD накопитель 120ГБ");names.add("Тверотельный накопитель SSD накопитель 240ГБ");names.add("Тверотельный накопитель SSD накопитель 480ГБ");
        price.add("2 800");price.add("4 900");price.add("12 500");
        disc.add("скорость чтение 510");disc.add("скорость запси 550");disc.add("скорость интерфейса 6гб");

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

    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    ///аналитичеснкие методы

    //самый дешевоый SSD, возвращаем индекс нужного товара
    public int minPriceSSD(String shop){
        int k=0; //индекс мин цены
        if (shop.equals("citi")) {

            long minPrice = dataS.price.get(0); //мин цена
            for (int i = 0; i < dataS.price.size()-1; i++) {
                if (dataS.price.get(i) < minPrice & dataS.name.get(i).contains("SSD") & (dataS.name.get(i).contains("Гб") | dataS.name.get(i).contains("Gb"))) {// & dataS.name.get(i).contains("SSD")
                    //получаем его цену
                    minPrice = dataS.price.get(i);
                    k = i+1;
                }
            }
        }
        else if(shop.equals("jast"))
        {
            long minPrice = dataJ.price.get(0); //мин цена
            for (int i = 0; i < dataJ.price.size()-1; i++) {//(dataJ.name.get(i).contains("SSD")) &
                if (dataJ.price.get(i) < minPrice & dataJ.name.get(i).contains("SSD") & (dataJ.name.get(i).contains("Гб") | dataJ.name.get(i).contains("Gb"))) {//  & dataJ.name.get(i).contains("SSD")
                    //получаем его цену String b = a.replaceAll(" ", "")
                    minPrice = dataJ.price.get(i);
                    k = i+1;
                }
            }

        }
        return k;
    }

    //самый дорогой SSD, возвращаем индекс нужного товара
    public int maxPriceSSD(String shop){
        int k=0; //индекс мин цены
        if (shop.equals("citi")) {

            long minPrice = dataS.price.get(0); //мин цена
            for (int i = 0; i < dataS.price.size()-1; i++) {
                if (dataS.price.get(i) > minPrice) {
                    //получаем его цену
                    minPrice = dataS.price.get(i);
                    k = i;
                }
            }
        }
        else if(shop.equals("jast"))
        {
            long minPrice = dataJ.price.get(0); //мин цена
            for (int i = 0; i < dataJ.price.size()-1; i++) {//(dataJ.name.get(i).contains("SSD")) &
                if (dataJ.price.get(i) > minPrice) {
                    //получаем его цену String b = a.replaceAll(" ", "")
                    minPrice = dataJ.price.get(i);
                    k = i;
                }
            }

        }
        return k;
    }

    //средняя стоимость на ssd
    public long midPriceSSD(String shop){
        int k=0; //индекс мин цены
        long midPrice = 0; //мин цена
        if (shop.equals("citi")) {
            for (int i = 0; i < dataS.price.size()-1; i++) {
                if(dataS.name.get(i).contains("SSD") & (dataS.name.get(i).contains("Гб") | dataS.name.get(i).contains("Gb"))){
                midPrice+=dataS.price.get(i);}
            }
        }
        else if(shop.equals("jast"))
        {
            for (int i = 0; i < dataJ.price.size()-1; i++) {
                if(dataJ.name.get(i).contains("SSD") & (dataJ.name.get(i).contains("Гб") | dataJ.name.get(i).contains("Gb"))){
                midPrice+=dataJ.price.get(i);}
            }
        }
        return midPrice;
    }
}
