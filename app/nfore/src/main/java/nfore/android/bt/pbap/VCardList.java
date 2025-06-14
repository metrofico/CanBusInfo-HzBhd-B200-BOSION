package nfore.android.bt.pbap;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes3.dex */
public class VCardList implements Serializable {
    private List<VCardPack> vcardPacks;

    public List<VCardPack> getVcardPacks() {
        return this.vcardPacks;
    }

    public void setVcardPacks(List<VCardPack> list) {
        this.vcardPacks = list;
    }
}
