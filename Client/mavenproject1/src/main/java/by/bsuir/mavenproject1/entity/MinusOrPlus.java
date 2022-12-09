
package by.bsuir.mavenproject1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Jacksonized @Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MinusOrPlus {
    private String idUser;
    private String name;
    private String summ;
    private String mess;
    private String minusOrPlus;
    
}
