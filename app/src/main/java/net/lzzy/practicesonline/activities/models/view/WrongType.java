package net.lzzy.practicesonline.activities.models.view;

/**
 * Created by lzzy_gxy on 2019/5/8.
 * Description:
 */
public enum WrongType {
    RIGHT_OPTIONS("正确"),MISS_OPTIONS("少选"),EXTRA_OPTIONS("多选"),
    WRONG_OPTIONS("错选");
    private String name;

    WrongType(String name){
        this.name=name;
    }
    @Override
    public String toString(){
        return name;
    }

    public static WrongType getInstance(int index){
        for (WrongType type:WrongType.values()){
            if (type.ordinal()==index){
                return type;
            }
        }
        return null;
    }
}
