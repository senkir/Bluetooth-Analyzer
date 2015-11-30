package enyeinteractive.com.bluetoothanalyzer.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;


@Table(databaseName = AppDatabase.NAME)
public class Service {

    @Column
    @PrimaryKey
    long id;

    @Column
    String uuid;

    @Column
    String alias;

    @Column
    String deviceId;

}
