package com.example.cape_medics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FireEngine extends AppCompatActivity {
    JSONObject fireEngine;
    Spinner engine,fuelSpinner;
    TextView dateView;
    String Date;
    CheckBox air,antennae,battery,body,brake,branding,dashboard,emergency,exhaust,oil,fuelLevel,headlights,leftIndicator,rightIndicator,back,front,jack,leds,licence,plates,radio,rear,reverse,side,siren,spare,tread,pressure,windows,windscreen,waterSpritzer,c02,dcp,fuelFunnel,awgPencil,gekkaTwister,Pistol65,falseSpindles,layflat25,short65,duraflex65,duraflex38,afff,boltCutter,wireCutter,crowBar,hondaKey,hondaPump,highPump,rakeHoe,bushBeater,jerryCan,softSuction,strainer,towRope,meterStandpipe,standpipeKey,blankCoupling65,blankCoupling25,controlDivider,gekkaReducer,gekkaDivider,baBottles,baSets,trafficCones,bobejaan,toolbox,trashpump,combiTool,batteries,charger,chains,cutters,batteriesCutters,chargerCutter,ram,batteriesRam,ramExtension,saw,spareBlades,batteriesSaw,chargerSaw,chockBlocks,shovel;
    EditText airComment,antennaeComment,batteryComment,bodyComment,brakeComment,brandingComment,dashboardComment,emergencyComment,exhaustComment,oilComment,fuelLevelComment,headlightsComment,leftIndicatorComment,rightIndicatorComment,backComment,frontComment,jackComment,ledsComment,licenceComment,platesComment,radioComment,rearComment,reverseComment,sideComment,sirenComment,spareComment,treadComment,pressureComment,windowsComment,windscreenComment,waterSpritzerComment,c02Comment,dcpComment,fuelFunnelComment,awgPencilComment,gekkaTwisterComment,Pistol65Comment,falseSpindlesComment,layflat25Comment,short65Comment,duraflex65Comment,duraflex38Comment,afffComment,boltCutterComment,wireCutterComment,crowBarComment,hondaKeyComment,hondaPumpComment,highPumpComment,rakeHoeComment,bushBeaterComment,jerryCanComment,softSuctionComment,strainerComment,towRopeComment,meterStandpipeComment,standpipeKeyComment,blankCoupling65Comment,blankCoupling25Comment,controlDividerComment,gekkaReducerComment,gekkaDividerComment,baBottlesComment,baSetsComment,trafficConesComment,bobejaanComment,toolboxComment,trashpumpComment,combiToolComment,batteriesComment,chargerComment,chainsComment,cuttersComment,batteriesCuttersComment,chargerCutterComment,ramComment,batteriesRamComment,ramExtensionComment,sawComment,spareBladesComment,batteriesSawComment,chargerSawComment,chockBlocksComment,shovelComment;
    List<CheckBox> checkBoxList;
    List<EditText> commentList;
    EditText licenceExpire,driver,mileage,oils,reg,location,producion,crew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_engine2);
        fireEngine = new JSONObject();
        licenceExpire = findViewById(R.id.licenceExpireEdit);
        driver = findViewById(R.id.driverEdit);
        mileage = findViewById(R.id.mileageEdit);
        oils = findViewById(R.id.oilsEdit);
        reg = findViewById(R.id.regEdit);
        location = findViewById(R.id.locationEdit);
        producion = findViewById(R.id.productionEdit);
        crew = findViewById(R.id.crewEdit);

        fuelSpinner = findViewById(R.id.fuelSpinner);
        engine = findViewById(R.id.vehicleNumberSpinner);
        dateView = findViewById(R.id.dateView);
        air = findViewById(R.id.air); antennae = findViewById(R.id.antennae); battery= findViewById(R.id.battery);body= findViewById(R.id.body);brake= findViewById(R.id.brake);branding = findViewById(R.id.branding);dashboard = findViewById(R.id.dashboard);emergency = findViewById(R.id.emergency);exhaust = findViewById(R.id.exhaust);oil = findViewById(R.id.oil);fuelLevel = findViewById(R.id.fuelLevel);headlights = findViewById(R.id.headlights);leftIndicator = findViewById(R.id.leftIndicator);rightIndicator = findViewById(R.id.rightIndicator);back = findViewById(R.id.back);front = findViewById(R.id.front);jack = findViewById(R.id.jack);leds = findViewById(R.id.leds);licence = findViewById(R.id.licence);plates = findViewById(R.id.plates);radio = findViewById(R.id.radio);rear = findViewById(R.id.rear);reverse = findViewById(R.id.reverse);side = findViewById(R.id.side);siren = findViewById(R.id.siren);spare = findViewById(R.id.spare);tread = findViewById(R.id.tread);pressure = findViewById(R.id.pressure);windows = findViewById(R.id.windows);windscreen = findViewById(R.id.windscreen);waterSpritzer = findViewById(R.id.waterSpritzer);c02 = findViewById(R.id.c02);dcp = findViewById(R.id.dcp);fuelFunnel = findViewById(R.id.fuelFunnel);awgPencil = findViewById(R.id.awgPencil);gekkaTwister = findViewById(R.id.gekkaTwister);Pistol65 = findViewById(R.id.Pistol65);falseSpindles = findViewById(R.id.falseSpindles);layflat25 = findViewById(R.id.layflat25);short65 = findViewById(R.id.short65);duraflex65 = findViewById(R.id.duraflex65);duraflex38 = findViewById(R.id.duraflex38);afff = findViewById(R.id.afff);boltCutter = findViewById(R.id.boltCutter);wireCutter = findViewById(R.id.wireCutter);crowBar = findViewById(R.id.crowBar);hondaKey = findViewById(R.id.hondaKey);hondaPump = findViewById(R.id.hondaPump);highPump = findViewById(R.id.highPump);rakeHoe = findViewById(R.id.rakeHoe);bushBeater = findViewById(R.id.bushBeater);jerryCan = findViewById(R.id.jerryCan);softSuction = findViewById(R.id.softSuction);strainer = findViewById(R.id.strainer);towRope = findViewById(R.id.towRope);meterStandpipe = findViewById(R.id.meterStandpipe);standpipeKey = findViewById(R.id.standpipeKey);blankCoupling65 = findViewById(R.id.blankCoupling65);blankCoupling25 = findViewById(R.id.blankCoupling25);controlDivider = findViewById(R.id.controlDivider);gekkaReducer = findViewById(R.id.gekkaReducer);gekkaDivider = findViewById(R.id.gekkaDivider);baBottles = findViewById(R.id.baBottles);baSets = findViewById(R.id.baSets);trafficCones = findViewById(R.id.trafficCones);bobejaan = findViewById(R.id.bobejaan);toolbox = findViewById(R.id.toolbox);trashpump = findViewById(R.id.trashpump);combiTool = findViewById(R.id.combiTool);batteries = findViewById(R.id.batteries);charger = findViewById(R.id.charger);chains = findViewById(R.id.chains);cutters = findViewById(R.id.cutters);batteriesCutters = findViewById(R.id.batteriesCutters);chargerCutter = findViewById(R.id.chargerCutter);ram = findViewById(R.id.ram);batteriesRam = findViewById(R.id.batteriesRam);ramExtension = findViewById(R.id.ramExtension);saw = findViewById(R.id.saw);spareBlades = findViewById(R.id.spareBlades);batteriesSaw = findViewById(R.id.batteriesSaw);chargerSaw = findViewById(R.id.chargerSaw);chockBlocks = findViewById(R.id.chockBlocks);shovel = findViewById(R.id.shovel);
        airComment = findViewById(R.id.airComment); antennaeComment = findViewById(R.id.antennaeComment); batteryComment = findViewById(R.id.batteryComment);bodyComment= findViewById(R.id.bodyComment);brakeComment = findViewById(R.id.brakeComment);brandingComment = findViewById(R.id.brandingComment);dashboardComment = findViewById(R.id.dashboardComment);emergencyComment = findViewById(R.id.emergencyComment);exhaustComment = findViewById(R.id.exhaustComment);oilComment = findViewById(R.id.oilComment);fuelLevelComment = findViewById(R.id.fuelLevelComment);headlightsComment = findViewById(R.id.headlightsComment);leftIndicatorComment = findViewById(R.id.leftIndicatorComment);rightIndicatorComment = findViewById(R.id.rightIndicatorComment);backComment = findViewById(R.id.backComment);frontComment = findViewById(R.id.frontComment);jackComment = findViewById(R.id.jackComment);ledsComment = findViewById(R.id.ledsComment);licenceComment = findViewById(R.id.licenceComment);platesComment = findViewById(R.id.platesComment);radioComment = findViewById(R.id.radioComment);rearComment = findViewById(R.id.rearComment);reverseComment = findViewById(R.id.reverseComment);sideComment = findViewById(R.id.sideComment);sirenComment = findViewById(R.id.sirenComment);spareComment = findViewById(R.id.spareComment);treadComment = findViewById(R.id.treadComment);pressureComment = findViewById(R.id.pressureComment);windowsComment = findViewById(R.id.windowsComment);windscreenComment = findViewById(R.id.windscreenComment);waterSpritzerComment = findViewById(R.id.waterSpritzerComment);c02Comment = findViewById(R.id.c02Comment);dcpComment = findViewById(R.id.dcpComment);fuelFunnelComment = findViewById(R.id.fuelFunnelComment);awgPencilComment = findViewById(R.id.awgPencilComment);gekkaTwisterComment = findViewById(R.id.gekkaTwisterComment);Pistol65Comment = findViewById(R.id.Pistol65Comment);falseSpindlesComment = findViewById(R.id.falseSpindlesComment);layflat25Comment = findViewById(R.id.layflat25Comment);short65Comment = findViewById(R.id.short65Comment);duraflex65Comment = findViewById(R.id.duraflex65Comment);duraflex38Comment = findViewById(R.id.duraflex38Comment);afffComment = findViewById(R.id.afffComment);boltCutterComment = findViewById(R.id.boltCutterComment);wireCutterComment = findViewById(R.id.wireCutterComment);crowBarComment = findViewById(R.id.crowBarComment);hondaKeyComment = findViewById(R.id.hondaKeyComment);hondaPumpComment = findViewById(R.id.hondaPumpComment);highPumpComment = findViewById(R.id.highPumpComment);rakeHoeComment = findViewById(R.id.rakeHoeComment);bushBeaterComment = findViewById(R.id.bushBeaterComment);jerryCanComment = findViewById(R.id.jerryCanComment);softSuctionComment = findViewById(R.id.softSuctionComment);strainerComment = findViewById(R.id.strainerComment);towRopeComment = findViewById(R.id.towRopeComment);meterStandpipeComment = findViewById(R.id.meterStandpipeComment);standpipeKeyComment = findViewById(R.id.standpipeKeyComment);blankCoupling65Comment = findViewById(R.id.blankCoupling65Comment);blankCoupling25Comment = findViewById(R.id.blankCoupling25Comment);controlDividerComment = findViewById(R.id.controlDividerComment);gekkaReducerComment = findViewById(R.id.gekkaReducerComment);gekkaDividerComment = findViewById(R.id.gekkaDividerComment);baBottlesComment = findViewById(R.id.baBottlesComment);baSetsComment = findViewById(R.id.baSetsComment);trafficConesComment = findViewById(R.id.trafficConesComment);bobejaanComment = findViewById(R.id.bobejaanComment);toolboxComment = findViewById(R.id.toolboxComment);trashpumpComment = findViewById(R.id.trashpumpComment);combiToolComment = findViewById(R.id.combiToolComment);batteriesComment = findViewById(R.id.batteriesComment);chargerComment = findViewById(R.id.chargerComment);chainsComment = findViewById(R.id.chainsComment);cuttersComment = findViewById(R.id.cuttersComment);batteriesCuttersComment = findViewById(R.id.batteriesCuttersComment);chargerCutterComment = findViewById(R.id.chargerCutterComment);ramComment = findViewById(R.id.ramComment);batteriesRamComment = findViewById(R.id.batteriesRamComment);ramExtensionComment = findViewById(R.id.ramExtensionComment);sawComment = findViewById(R.id.sawComment);spareBladesComment = findViewById(R.id.spareBladesComment);batteriesSawComment = findViewById(R.id.batteriesSawComment);chargerSawComment = findViewById(R.id.chargerSawComment);chockBlocksComment = findViewById(R.id.chockBlocksComment);shovelComment = findViewById(R.id.shovelComment);

        String[] Engine = {"ISUZU","FUSO"};
        ArrayAdapter<String> engineAdapter = new ArrayAdapter<>(this,
                R.layout.custom_spinner,Engine);

        engineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        engine.setAdapter(engineAdapter);


        String[] Fuel = {"Empty","Half","Full"};

        ArrayAdapter<String> fuelAdapter = new ArrayAdapter<>(this,
                R.layout.custom_spinner,Fuel);

        fuelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fuelSpinner.setAdapter(fuelAdapter);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String DateTime = dateFormat.format(date);
        Date = DateTime.substring(0,10);
        dateView.setText(Date);

        checkBoxList = Arrays.asList(air,antennae,battery,body,brake,branding,dashboard,emergency,exhaust,oil,fuelLevel,headlights,leftIndicator,rightIndicator,back,front,jack,leds,licence,plates,radio,rear,reverse,side,siren,spare,tread,pressure,windows,windscreen,waterSpritzer,c02,dcp,fuelFunnel,awgPencil,gekkaTwister,Pistol65,falseSpindles,layflat25,short65,duraflex65,duraflex38,afff,boltCutter,wireCutter,crowBar,hondaKey,hondaPump,highPump,rakeHoe,bushBeater,jerryCan,softSuction,strainer,towRope,meterStandpipe,standpipeKey,blankCoupling65,blankCoupling25,controlDivider,gekkaReducer,gekkaDivider,baBottles,baSets,trafficCones,bobejaan,toolbox,trashpump,combiTool,batteries,charger,chains,cutters,batteriesCutters,chargerCutter,ram,batteriesRam,ramExtension,saw,spareBlades,batteriesSaw,chargerSaw,chockBlocks,shovel);
        commentList = Arrays.asList(airComment,antennaeComment,batteryComment,bodyComment,brakeComment,brandingComment,dashboardComment,emergencyComment,exhaustComment,oilComment,fuelLevelComment,headlightsComment,leftIndicatorComment,rightIndicatorComment,backComment,frontComment,jackComment,ledsComment,licenceComment,platesComment,radioComment,rearComment,reverseComment,sideComment,sirenComment,spareComment,treadComment,pressureComment,windowsComment,windscreenComment,waterSpritzerComment,c02Comment,dcpComment,fuelFunnelComment,awgPencilComment,gekkaTwisterComment,Pistol65Comment,falseSpindlesComment,layflat25Comment,short65Comment,duraflex65Comment,duraflex38Comment,afffComment,boltCutterComment,wireCutterComment,crowBarComment,hondaKeyComment,hondaPumpComment,highPumpComment,rakeHoeComment,bushBeaterComment,jerryCanComment,softSuctionComment,strainerComment,towRopeComment,meterStandpipeComment,standpipeKeyComment,blankCoupling65Comment,blankCoupling25Comment,controlDividerComment,gekkaReducerComment,gekkaDividerComment,baBottlesComment,baSetsComment,trafficConesComment,bobejaanComment,toolboxComment,trashpumpComment,combiToolComment,batteriesComment,chargerComment,chainsComment,cuttersComment,batteriesCuttersComment,chargerCutterComment,ramComment,batteriesRamComment,ramExtensionComment,sawComment,spareBladesComment,batteriesSawComment,chargerSawComment,chockBlocksComment,shovelComment);
    }

    public void Send(View v){
        try{
            fireEngine.put("Licence Expire", licenceExpire.toString());
            fireEngine.put("Date", Date);
            fireEngine.put("Driver", driver.toString());
            fireEngine.put("Fire Engine", String.valueOf(engine));
            fireEngine.put("Mileage", mileage.toString());
            fireEngine.put("Fuel", String.valueOf(fuelSpinner));
            fireEngine.put("Oils", oils.toString());
            fireEngine.put("Reg No.", reg.toString());
            fireEngine.put("Location.", location.toString());
            fireEngine.put("Production", producion.toString());
            fireEngine.put("Crew", crew.toString());

        }catch(Exception e){}

        for (int i = 0; i<checkBoxList.size();i++){
            if(!checkBoxList.get(i).isChecked()){
                try{
                    fireEngine.put(checkBoxList.get(i).getText().toString(),commentList.get(i).toString());
                }catch(Exception e){}
            }
        }
    }
}
