package edu.agregator.ejb;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

import java.util.List;

/**
 * Created by 1 on 25.07.2017.
 */
public class pars{
    public static List<String> k;
    public static void parss() {
        //спарсим сайт ситилинка
        GithubRepoPageProcessor parsDir = new GithubRepoPageProcessor();
        //Spider.create(new GithubRepoPageProcessor()).getSpiderListeners();
        Spider.create(parsDir)
                // From "https://github.com/code4craft" began to grasp
                .addUrl("https://www.citilink.ru/catalog/computers_and_notebooks/hdd/ssd_in")
                .addPipeline(new JsonFilePipeline("C:/webmagic/"))
                // Open 5 threads of Crawl
                .thread(10)
                // Start Crawl
                .run();

        //парсим сайт dns
        GithubRepoPageProcessor parsDirD = new GithubRepoPageProcessor();
        //Spider.create(new GithubRepoPageProcessor()).getSpiderListeners();
        Spider.create(parsDirD)
                // From "https://github.com/code4craft" began to grasp
                .addUrl("https://www.dns-shop.ru/catalog/8a9ddfba20724e77/ssd-nakopiteli/")
                .addPipeline(new JsonFilePipeline("C:/webmagic/"))
                // Open 5 threads of Crawl
                .thread(10)
                // Start Crawl
                .run();
        //k.addAll(parsDir.names);
        System.out.println(parsDir.names);
        System.out.println(parsDir.disc);
    }
}
