package floobits.common.protocol.json.receive;

import floobits.common.protocol.Base;

public class SaveBuf implements Base {
    public Integer id;
    public String name = "saved";

    public SaveBuf(Integer id) {
        this.id = id;
    }
}
