/*
import com.lung.application.BootUserServiceApplication;
import com.lung.application.elasticsearch.ArticleSearchRepository;
import com.lung.application.es.Article;
import com.lung.application.es.Author;
import com.lung.application.es.Tutorial;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

*/
/**
 * @Title: ElasticsreachTest
 * @Author: lung
 * @Date: 19-3-12 下午1:49
 * @version: V1.0
 * @Description: Created with IntelliJ IDEA.
 **//*

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootUserServiceApplication.class)
public class ElasticsreachTest {

    @Autowired
    private ArticleSearchRepository articleSearchRepository;


    @Test
    public void contextLoads() {

    }

    @Test
    public void testArticleIndex() {

        Author author = new Author(2L, "龙展鹏", "测试");

        Tutorial tutorial = new Tutorial(2L, "java开发工程师");

        Article article = new Article();

        article.setId(2L);
        article.setTitle("软件开发工程师");
        article.setAbstracts("搜索");
        article.setAuthor(author);
        article.setTutorial(tutorial);
        article.setContent("全文搜索es框架整合");
        article.setPostTime("20190312");
        article.setClickCount("100");
        Article save = articleSearchRepository.save(article);
        System.out.println(save);
    }

    @Test
    public void testSearch() {
        String queryString = "工程师";
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        Iterable<Article> search = articleSearchRepository.search(builder);
        Iterator<Article> iterator = search.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }

    }

}
*/
