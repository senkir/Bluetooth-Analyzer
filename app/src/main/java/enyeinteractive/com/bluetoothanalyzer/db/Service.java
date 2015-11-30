package enyeinteractive.com.bluetoothanalyzer.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.OneToMany.Method;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

import java.util.List;


@Table(databaseName = AppDatabase.NAME)
public class Service  extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String uuid;

    @Column
    String alias;

    @Column
    @ForeignKey(references = {@ForeignKeyReference(columnName = "device_id",
            columnType = Long.class,
            foreignColumnName = "id")},
            saveForeignKeyModel = false)
    ForeignKeyContainer<Device> deviceContainer;

    List<Characteristic> characteristics;

    public void setDevice(Device device) {
        deviceContainer = new ForeignKeyContainer<>(Device.class);
        deviceContainer.setModel(device);
    }

    @OneToMany(methods = {Method.ALL}, variableName = "characteristics")
    public List<Characteristic> getCharacteristics() {
        if (characteristics == null) {
            characteristics = new Select()
                    .from(Characteristic.class)
                    .where(Condition.column(Characteristic$Table.SERVICECONTAINER_SERVICE_ID).is(id))
                    .queryList();
        }
        return characteristics;
    }

}
