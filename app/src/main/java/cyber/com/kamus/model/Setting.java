package cyber.com.kamus.model;

import cyber.com.kamus.view.fragment.FragmentPengaturan;

public class Setting {
    String name;
    FragmentPengaturan.TypePengaturan typePengaturan;

    public Setting(String name, FragmentPengaturan.TypePengaturan typePengaturan) {
        this.name = name;
        this.typePengaturan = typePengaturan;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FragmentPengaturan.TypePengaturan getTypePengaturan() {
        return typePengaturan;
    }

    public void setTypePengaturan(FragmentPengaturan.TypePengaturan typePengaturan) {
        this.typePengaturan = typePengaturan;
    }
}
