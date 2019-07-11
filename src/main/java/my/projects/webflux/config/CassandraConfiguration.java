package my.projects.webflux.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;


@Configuration
@ConditionalOnClass({Cluster.class, CassandraAdminOperations.class})
@EnableConfigurationProperties(CassandraProperties.class)
@EnableCassandraRepositories
public class CassandraConfiguration {

  private ObjectMapper objectMapper;
  private Cluster cluster;
  private Environment environment;
  private CassandraProperties properties;

  /**
   * Default constructor for Cassandra Configuration.
   *
   * @param objectMapper JSON object mapper
   * @param cluster cluster
   * @param environment environment
   * @param properties cassandra properties
   */
  public CassandraConfiguration(ObjectMapper objectMapper, Cluster cluster,
      Environment environment, CassandraProperties properties) {
    this.objectMapper = objectMapper;
    this.cluster = cluster;
    this.environment = environment;
    this.properties = properties;
  }

  /**
   * Keyspace creation.
   */
  @PostConstruct
  public void init() {
    if (environment
        .acceptsProfiles(Profiles.of(ProfileConstants.DEV))) {
      Session connect = cluster.connect();
      ResultSet execute = connect.execute("CREATE KEYSPACE IF NOT EXISTS "
          + properties.getKeyspaceName()
          + " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }"
          + " AND DURABLE_WRITES = true;");
      if (execute.wasApplied()) {
      }
      connect.close();
    }
  }

}
