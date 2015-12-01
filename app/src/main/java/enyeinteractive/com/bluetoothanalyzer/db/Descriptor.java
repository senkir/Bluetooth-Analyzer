package enyeinteractive.com.bluetoothanalyzer.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;


@Table(databaseName = AppDatabase.NAME)
public class Descriptor extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String uuid;

    @Column
    public String alias;

    @Column
    @ForeignKey(references = {@ForeignKeyReference(columnName = "characteristic_id",
            columnType = Long.class,
            foreignColumnName = "id")},
            saveForeignKeyModel = false)
    ForeignKeyContainer<Characteristic> characteristicContainer;

    public Characteristic getCharacteristic() {
        return characteristicContainer.toModel();
    }

    public void setCharacteristic(Characteristic service) {
        characteristicContainer = new ForeignKeyContainer<>(Characteristic.class);
        characteristicContainer.setModel(service);
    }


}
