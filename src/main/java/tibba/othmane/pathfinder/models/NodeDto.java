package tibba.othmane.pathfinder.models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Data
@Builder
public class NodeDto {
    int row;
    int col;
    boolean normal;
    boolean target;
    boolean obstacle;
    boolean start;
    boolean path;
    public NodeDto(int row, int col, boolean normal, boolean target, boolean obstacle, boolean start, boolean path) {
        this.row = row;
        this.col = col;
        this.normal = normal;
        this.target = target;
        this.obstacle = obstacle;
        this.start = start;
        this.path = path;
    }
}
