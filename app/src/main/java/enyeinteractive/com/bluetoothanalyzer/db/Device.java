package enyeinteractive.com.bluetoothanalyzer.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.OneToMany.Method;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

import java.util.List;


@ModelContainer
@Table(databaseName = AppDatabase.NAME)
public class Device extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String macAddress;

    @Column
    public String alias;

    List<Service> services;

    @OneToMany(methods = {Method.ALL}, variableName = "services")
    public List<Service> getServices() {
        if (services == null) {
            services = new Select()
                    .from(Service.class)
                    .where(Condition.column(Service$Table.DEVICECONTAINER_DEVICE_ID).is(id))
                    .queryList();
        }
        return services;
    }
}
