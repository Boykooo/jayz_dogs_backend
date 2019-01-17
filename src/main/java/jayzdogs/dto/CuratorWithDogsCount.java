package jayzdogs.dto;

import jayzdogs.entity.Curator;
import lombok.Data;
import lombok.Getter;

/**
 * @author andrey
 * @date 17.01.19
 */

@Getter
public class CuratorWithDogsCount {

    private Curator curator;
    private int count;

    public CuratorWithDogsCount(Curator curator, long count) {
        this.curator = curator;
        this.count = (int) count;
    }

    public boolean canTakeDog() {
        return this.count < this.curator.getDogsLimit();
    }

}
