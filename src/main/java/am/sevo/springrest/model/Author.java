package am.sevo.springrest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table (name = "author")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int age;


}
