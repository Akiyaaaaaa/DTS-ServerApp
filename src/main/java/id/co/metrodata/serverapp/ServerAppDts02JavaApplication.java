package id.co.metrodata.serverapp;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerAppDts02JavaApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerAppDts02JavaApplication.class, args);

    System.out.println();
    System.out.println("ServerApp is running...");
    System.out.println();
  }

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper
      .getConfiguration()
      .setMatchingStrategy(MatchingStrategies.STRICT);

    return modelMapper;
  }
}
