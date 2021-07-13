package org.zerock.ex02.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.ex02.entity.Movie;
import org.zerock.ex02.entity.MovieImage;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieImageRepository movieImageRepository;

    @Transactional
    @Commit
    @Test
    public void insertMovies(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Movie movie = Movie.builder().title("Movie..." + i).build();
            System.out.println("-------------------------------------");

            movieRepository.save(movie);

            int count = (int)(Math.random()*5) + 1;

            for(int j=0; j<count; j++){
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg").build();

                movieImageRepository.save(movieImage);
            }

            System.out.println("-------------------------------------");

        });
    }
}
