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
public class Characteristic  extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String uuid;

    @Column
    public String alias;

    @Column
    @ForeignKey(references = {@ForeignKeyReference(columnName = "service_id",
            columnType = Long.class,
            foreignColumnName = "id")},
            saveForeignKeyModel = false)
    ForeignKeyContainer<Service> serviceContainer;

    public void setService(Service service) {
        serviceContainer = new ForeignKeyContainer<>(Service.class);
        serviceContainer.setModel(service);
    }

    public Service getService() {
        return serviceContainer.toModel();
    }

    List<Descriptor> descriptors;

    @OneToMany(methods = {Method.ALL}, variableName = "descriptors")
    public List<Descriptor> getDescriptors() {
        if (descriptors == null) {
            descriptors = new Select()
                    .from(Descriptor.class)
                    .where(Condition.column(Descriptor$Table.CHARACTERISTICCONTAINER_CHARACTERISTIC_ID).is(id))
                    .queryList();
        }
        return descriptors;
    }

}
