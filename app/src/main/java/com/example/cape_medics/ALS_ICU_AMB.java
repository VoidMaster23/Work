package com.example.cape_medics;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ALS_ICU_AMB extends AppCompatActivity {
    Spinner VehicleNumber;
    TextView dateView;
    JSONObject als_icu_amb, response, load, save;
    List<CheckBox> checkBoxList;
    List<EditText> commentList;
    String Date, responseServer,url, code, saved;
    Cache cache;
    boolean connected;
    ScheduledExecutorService scheduledExecutorService;

    private  EditText driver, controller, checkedBy, inspectionTime;

    private CheckBox air;
    private CheckBox antennae;
    private CheckBox battery;
    private CheckBox body;
    private CheckBox brake;
    private CheckBox branding;
    private CheckBox dashboard;
    private CheckBox emergency;
    private CheckBox exhaust;
    private CheckBox oil;
    private CheckBox fuel;
    private CheckBox headlights;
    private CheckBox leftIndicator;
    private CheckBox rightIndicator;
    private CheckBox back;
    private CheckBox front;
    private CheckBox jack;
    private CheckBox leds;
    private CheckBox licence;
    private CheckBox plates;
    private CheckBox radio;
    private CheckBox rear;
    private CheckBox reverse;
    private CheckBox side;
    private CheckBox siren;
    private CheckBox spare;
    private CheckBox tread;
    private CheckBox pressure;
    private CheckBox windows;
    private CheckBox windscreen;
    private EditText airComment;
    private EditText antennaeComment;
    private EditText batteryComment;
    private EditText bodyComment;
    private EditText brakeComment;
    private EditText brandingComment;
    private EditText dashboardComment;
    private EditText emergencyComment;
    private EditText exhaustComment;
    private EditText oilComment;
    private EditText fuelComment;
    private EditText headlightsComment;
    private EditText leftIndicatorComment;
    private EditText rightIndicatorComment;
    private EditText backComment;
    private EditText frontComment;
    private EditText jackComment;
    private EditText ledsComment;
    private EditText licenceComment;
    private EditText platesComment;
    private EditText radioComment;
    private EditText rearComment;
    private EditText reverseComment;
    private EditText sideComment;
    private EditText sirenComment;
    private EditText spareComment;
    private EditText treadComment;
    private EditText pressureComment;
    private EditText windowsComment;
    private EditText windscreenComment;
    private CheckBox ecg;
    private TextView ecgQty;
    private EditText ecgComment;
    private CheckBox adultEcg;
    private TextView adultEcgQty;
    private EditText adultEcgComment;
    private CheckBox childEcg;
    private TextView childEcgQty;
    private EditText childEcgComment;
    private CheckBox spareBattery;
    private TextView spareBatteryQty;
    private EditText spareBatteryComment;
    private CheckBox spareEcg;
    private TextView spareEcgQty;
    private EditText spareEcgComment;
    private CheckBox oximeter;
    private TextView oximeterQty;
    private EditText oximeterComment;
    private CheckBox suctionUnit;
    private TextView suctionUnitQty;
    private EditText suctionUnitComment;
    private CheckBox suctionReservoir;
    private TextView suctionReservoirQty;
    private EditText suctionReservoirComment;
    private CheckBox chargingCable;
    private TextView chargingCableQty;
    private EditText chargingCableComment;
    private CheckBox suctionTubing;
    private TextView suctionTubingQty;
    private EditText suctionTubingComment;
    private CheckBox softSuction;
    private TextView softSuctionQty;
    private EditText softSuctionComment;
    private CheckBox adultSuction;
    private TextView adultSuctionQty;
    private EditText adultSuctionComment;
    private CheckBox paedSuction;
    private TextView paedSuctionQty;
    private EditText paedSuctionComment;
    private CheckBox ventilatorKit;
    private TextView ventilatorKitQty;
    private EditText ventilatorKitComment;
    private CheckBox circuitHose;
    private TextView circuitHoseQty;
    private EditText circuitHoseComment;
    private CheckBox circuitHoseFilter;
    private TextView circuitHoseFilterQty;
    private EditText circuitHoseFilterComment;
    private CheckBox co2Attachment;
    private TextView co2AttachmentQty;
    private EditText co2AttachmentComment;
    private CheckBox oxygenCylinder;
    private TextView oxygenCylinderQty;
    private EditText oxygenCylinderComment;
    private CheckBox oxygenGauge;
    private TextView oxygenGaugeQty;
    private EditText oxygenGaugeComment;
    private CheckBox oxygenCylinderKey;
    private TextView oxygenCylinderKeyQty;
    private EditText oxygenCylinderKeyComment;
    private CheckBox syringePump;
    private TextView syringePumpQty;
    private EditText syringePumpComment;
    private CheckBox powerCable;
    private TextView powerCableQty;
    private EditText powerCableComment;
    private CheckBox fiftySyringe;
    private TextView fiftySyringeQty;
    private EditText fiftySyringeComment;
    private CheckBox microbore;
    private TextView microboreQty;
    private EditText microboreComment;
    private CheckBox stretcher;
    private TextView stretcherQty;
    private EditText stretcherComment;
    private CheckBox straps;
    private TextView strapsQty;
    private EditText strapsComment;
    private CheckBox mattress;
    private TextView mattressQty;
    private EditText mattressComment;
    private CheckBox sheet;
    private TextView sheetQty;
    private EditText sheetComment;
    private CheckBox pillow;
    private TextView pillowQty;
    private EditText pillowComment;
    private CheckBox pillowCase;
    private TextView pillowCaseQty;
    private EditText pillowCaseComment;
    private CheckBox blanket;
    private TextView blanketQty;
    private EditText blanketComment;
    private CheckBox other;
    private TextView otherQty;
    private EditText otherComment;
    private CheckBox adultKed;
    private TextView adultKedQty;
    private EditText adultKedComment;
    private CheckBox childKed;
    private TextView childKedQty;
    private EditText childKedComment;
    private CheckBox adultSplint;
    private TextView adultSplintQty;
    private EditText adultSplintComment;
    private CheckBox scoopStretcher;
    private TextView scoopStretcherQty;
    private EditText scoopStretcherComment;
    private CheckBox spinalBoard;
    private TextView spinalBoardQty;
    private EditText spinalBoardComment;
    private CheckBox headBlocks;
    private TextView headBlocksQty;
    private EditText headBlocksComment;
    private CheckBox basePlate;
    private TextView basePlateQty;
    private EditText basePlateComment;
    private CheckBox spiderHarness;
    private TextView spiderHarnessQty;
    private EditText spiderHarnessComment;
    private CheckBox headStraps;
    private TextView headStrapsQty;
    private EditText headStrapsComment;
    private CheckBox zipStretcher;
    private TextView zipStretcherBoardQty;
    private EditText zipStretcherComment;
    private CheckBox longSplints;
    private TextView longSplintsQty;
    private EditText longSplintsComment;
    private CheckBox shortSplints;
    private TextView shortSplintsQty;
    private EditText shortSplintsComment;
    private CheckBox adultCollar;
    private TextView adultCollarQty;
    private EditText adultCollarComment;
    private CheckBox childCollar;
    private TextView childCollarQty;
    private EditText childCollarComment;
    private CheckBox fireExtinguisher;
    private TextView fireExtinguisherQty;
    private EditText fireExtinguisherComment;
    private CheckBox rescueHelmet;
    private TextView rescueHelmetQty;
    private EditText rescueHelmetComment;
    private CheckBox roadCones;
    private TextView roadConesQty;
    private EditText roadConesComment;
    private CheckBox reflectorVests;
    private TextView reflectorVestsQty;
    private EditText reflectorVestsComment;
    private CheckBox wasteBin;
    private TextView wasteBinQty;
    private EditText wasteBinComment;
    private CheckBox medicalWaste;
    private TextView medicalWasteQty;
    private EditText medicalWasteComment;
    private CheckBox sharpsBin;
    private TextView sharpsBinQty;
    private EditText sharpsBinComment;
    private CheckBox bodyBag;
    private TextView bodyBagQty;
    private EditText bodyBagComment;
    private CheckBox gloves;
    private TextView glovesQty;
    private EditText glovesComment;
    private CheckBox portableOxygen;
    private TextView portableOxygenQty;
    private EditText portableOxygenComment;
    private CheckBox pinRegulator;
    private TextView pinRegulatorQty;
    private EditText pinRegulatorComment;
    private CheckBox oxygenMainline;
    private TextView oxygenMainlineQty;
    private EditText oxygenMainlineComment;
    private CheckBox oxygenFlowMeter;
    private TextView oxygenFlowMeterQty;
    private EditText oxygenFlowMeterComment;
    private CheckBox bullNose;
    private TextView bullNoseQty;
    private EditText bullNoseComment;
    private CheckBox aed;
    private TextView aedQty;
    private EditText aedComment;
    private CheckBox adultDefibPads;
    private TextView adultDefibPadsQty;
    private EditText adultDefibPadsComment;
    private CheckBox childDefibPads;
    private TextView childDefibPadsQty;
    private EditText childDefibPadsComment;
    private CheckBox defibGel;
    private TextView defibGelQty;
    private EditText defibGelComment;
    private CheckBox asprin;
    private TextView asprinQty;
    private EditText asprinComment;
    private CheckBox gtn;
    private TextView gtnQty;
    private EditText gtnComment;
    private CheckBox clopidogrel;
    private TextView clopidogrelQty;
    private EditText clopidogrelComment;
    private CheckBox adenosine;
    private TextView adenosineQty;
    private EditText adenosineComment;
    private CheckBox amiodarone;
    private TextView amiodaroneQty;
    private EditText amiodaroneComment;
    private CheckBox lignocaine;
    private TextView lignocaineQty;
    private EditText lignocaineComment;
    private CheckBox adrenaline;
    private TextView adrenalineQty;
    private EditText adrenalineComment;
    private CheckBox atropine;
    private TextView atropineQty;
    private EditText atropineComment;
    private CheckBox magnesium;
    private TextView magnesiumQty;
    private EditText magnesiumComment;
    private CheckBox calcium;
    private TextView calciumQty;
    private EditText calciumComment;
    private CheckBox soda;
    private TextView sodaQty;
    private EditText sodaComment;
    private CheckBox thiamine;
    private TextView thiamineQty;
    private EditText thiamineComment;
    private CheckBox diazepam;
    private TextView diazepamQty;
    private EditText diazepamComment;
    private CheckBox midazolam;
    private TextView midazolamQty;
    private EditText midazolamComment;
    private CheckBox promethazine;
    private TextView promethazineQty;
    private EditText promethazineComment;
    private CheckBox activatedCharcoal;
    private TextView activatedCharcoalQty;
    private EditText activatedCharcoalComment;
    private CheckBox flumazenil;
    private TextView flumazenilQty;
    private EditText flumazenilComment;
    private CheckBox naloxone;
    private TextView naloxoneQty;
    private EditText naloxoneComment;
    private CheckBox morphine;
    private TextView morphineQty;
    private EditText morphineComment;
    private CheckBox dextrose;
    private TextView dextroseQty;
    private EditText dextroseComment;
    private CheckBox glucose;
    private TextView glucoseQty;
    private EditText glucoseComment;
    private CheckBox fenoterol;
    private TextView fenoterolQty;
    private EditText fenoterolComment;
    private CheckBox bromide;
    private TextView bromideQty;
    private EditText bromideComment;
    private CheckBox corticosteriods;
    private TextView corticosteriodsQty;
    private EditText corticosteriodsComment;
    private CheckBox furosemide;
    private TextView furosemideQty;
    private EditText furosemideComment;
    private CheckBox metaclopramide;
    private TextView metaclopramideQty;
    private EditText metaclopramideComment;
    private CheckBox buscopan;
    private TextView buscopanQty;
    private EditText buscopanComment;
    private CheckBox bigPlasters;
    private TextView bigPlastersQty;
    private EditText bigPlastersComment;
    private CheckBox smallPlasters;
    private TextView smallPlastersQty;
    private EditText smallPlastersComment;
    private CheckBox cetrimide;
    private TextView cetrimideQty;
    private EditText cetrimideComment;
    private CheckBox elastoplastRoll;
    private TextView elastoplastRollQty;
    private EditText elastoplastRollComment;
    private CheckBox prf;
    private TextView prfQty;
    private EditText prfComment;
    private CheckBox doa;
    private TextView doaQty;
    private EditText doaComment;
    private CheckBox gop;
    private TextView gopQty;
    private EditText gopComment;
    private CheckBox torniquet;
    private TextView torniquetQty;
    private EditText torniquetComment;
    private CheckBox glucometer;
    private TextView glucometerQty;
    private EditText glucometerComment;
    private CheckBox gloves2;
    private TextView gloves2Qty;
    private EditText gloves2Comment;
    private CheckBox bp;
    private TextView bpQty;
    private EditText bpComment;
    private CheckBox stethoscope;
    private TextView stethoscopeQty;
    private EditText stethoscopeComment;
    private CheckBox rescueScissors;
    private TextView rescueScissorsQty;
    private EditText rescueScissorsComment;
    private CheckBox thermometer;
    private TextView thermometerQty;
    private EditText thermometerComment;
    private CheckBox pupilTorch;
    private TextView pupilTorchQty;
    private EditText pupilTorchComment;
    private CheckBox testStrips;
    private TextView testStripsQty;
    private EditText testStripsComment;
    private CheckBox lancets;
    private TextView lancetsQty;
    private EditText lancetsComment;
    private CheckBox sats;
    private TextView satsQty;
    private EditText satsComment;
    private CheckBox adultMask;
    private TextView adultMaskQty;
    private EditText adultMaskComment;
    private CheckBox adultRebreather;
    private TextView adultRebreatherQty;
    private EditText adultRebreatherComment;
    private CheckBox adultNebulizer;
    private TextView adultNebulizerQty;
    private EditText adultNebulizerComment;
    private CheckBox adultNasal;
    private TextView adultNasalQty;
    private EditText adultNasalComment;
    private CheckBox childMask;
    private TextView childMaskQty;
    private EditText childMaskComment;
    private CheckBox childRebreather;
    private TextView childRebreatherQty;
    private EditText childRebreatherComment;
    private CheckBox childNebuliser;
    private TextView childNebuliserQty;
    private EditText childNebuliserComment;
    private CheckBox childNasal;
    private TextView childNasalQty;
    private EditText childNasalComment;
    private CheckBox traumaPads;
    private TextView traumaPadsQty;
    private EditText traumaPadsComment;
    private CheckBox traumaDressing100;
    private TextView traumaDressing100Qty;
    private EditText traumaDressing100Comment;
    private CheckBox traumaDressing200;
    private TextView traumaDressing200Qty;
    private EditText traumaDressing200Comment;
    private CheckBox bandage75;
    private TextView bandage75Qty;
    private EditText bandage75Comment;
    private CheckBox bandage50;
    private TextView bandage50Qty;
    private EditText bandage50Comment;
    private CheckBox stretchBandage;
    private TextView stretchBandageQty;
    private EditText stretchBandageComment;
    private CheckBox elastoplast;
    private TextView elastoplastQty;
    private EditText elastoplastComment;
    private CheckBox adultBvm;
    private TextView adultBvmQty;
    private EditText adultBvmComment;
    private CheckBox childBvm;
    private TextView childBvmQty;
    private EditText childBvmComment;
    private CheckBox infantBvm;
    private TextView infantBvmQty;
    private EditText infantBvmComment;
    private CheckBox adultMagilles;
    private TextView adultMagillesQty;
    private EditText adultMagillesComment;
    private CheckBox childMagilles;
    private TextView childMagillesQty;
    private EditText childMagillesComment;
    private CheckBox tube000;
    private TextView tube000Qty;
    private EditText tube000Comment;
    private CheckBox tube00;
    private TextView tube00Qty;
    private EditText tube00Comment;
    private CheckBox tube0;
    private TextView tube0Qty;
    private EditText tube0Comment;
    private CheckBox tube1;
    private TextView tube1Qty;
    private EditText tube1Comment;
    private CheckBox tube2;
    private TextView tube2Qty;
    private EditText tube2Comment;
    private CheckBox tube3;
    private TextView tube3Qty;
    private EditText tube3Comment;
    private CheckBox tube4;
    private TextView tube4Qty;
    private EditText tube4Comment;
    private CheckBox tube5;
    private TextView tube5Qty;
    private EditText tube5Comment;
    private CheckBox lma5;
    private TextView lma5Qty;
    private EditText lma5Comment;
    private CheckBox lma4;
    private TextView lma4Qty;
    private EditText lma4Comment;
    private CheckBox lma3;
    private TextView lma3Qty;
    private EditText lma3Comment;
    private CheckBox lma2;
    private TextView lma2Qty;
    private EditText lma2Comment;
    private CheckBox lma1;
    private TextView lma1Qty;
    private EditText lma1Comment;
    private CheckBox uvcPack;
    private TextView uvcPackQty;
    private EditText uvcPackComment;
    private CheckBox maternity;
    private TextView maternityQty;
    private EditText maternityComment;
    private CheckBox needleCric;
    private TextView needleCricQty;
    private EditText needleCricComment;
    private CheckBox ngTube;
    private TextView ngTubeQty;
    private EditText ngTubeComment;
    private CheckBox airwayPack;
    private TextView airwayPackQty;
    private EditText airwayPackComment;
    private CheckBox urinaryCatheter;
    private TextView urinaryCatheterQty;
    private EditText urinaryCatheterComment;
    private CheckBox urineBag;
    private TextView urineBagQty;
    private EditText urineBagComment;
    private CheckBox suturePack;
    private TextView suturePackQty;
    private EditText suturePackComment;
    private CheckBox spirometer;
    private TextView spirometerQty;
    private EditText spirometerComment;
    private CheckBox ringers1000;
    private TextView ringers1000Qty;
    private EditText ringers1000Comment;
    private CheckBox sodiumChloride;
    private TextView sodiumChlorideQty;
    private EditText sodiumChlorideComment;
    private CheckBox colloid;
    private TextView colloidQty;
    private EditText colloidComment;
    private CheckBox canulla14;
    private TextView canulla14Qty;
    private EditText canulla14Comment;
    private CheckBox canulla16;
    private TextView canulla16Qty;
    private EditText canulla16Comment;
    private CheckBox canulla18;
    private TextView canulla18Qty;
    private EditText canulla18Comment;
    private CheckBox canulla20;
    private TextView canulla20Qty;
    private EditText canulla20Comment;
    private CheckBox canulla22;
    private TextView canulla22Qty;
    private EditText canulla22Comment;
    private CheckBox canulla24;
    private TextView canulla24Qty;
    private EditText canulla24Comment;
    private CheckBox admin60;
    private TextView admin60Qty;
    private EditText admin60Comment;
    private CheckBox admin20;
    private TextView admin20Qty;
    private EditText admin20Comment;
    private CheckBox admin15;
    private TextView admin15Qty;
    private EditText admin15Comment;
    private CheckBox admin10;
    private TextView admin10Qty;
    private EditText admin10Comment;
    private CheckBox webcol;
    private TextView webcolQty;
    private EditText webcolComment;
    private CheckBox tegaderm;
    private TextView tegadermQty;
    private EditText tegadermComment;
    private CheckBox syringe50;
    private TextView syringe50Qty;
    private EditText syringe50Comment;
    private CheckBox syringe20;
    private TextView syringe20Qty;
    private EditText syringe20Comment;
    private CheckBox syringe10;
    private TextView syringe10Qty;
    private EditText syringe10Comment;
    private CheckBox syringe5;
    private TextView syringe5Qty;
    private EditText syringe5Comment;
    private CheckBox syringe3;
    private TextView syringe3Qty;
    private EditText syringe3Comment;
    private CheckBox needle18;
    private TextView needle18Qty;
    private EditText needle18Comment;
    private CheckBox needle21;
    private TextView needle21Qty;
    private EditText needle21Comment;
    private CheckBox handle;
    private TextView handleQty;
    private EditText handleComment;
    private CheckBox blade4;
    private TextView blade4Qty;
    private EditText blade4Comment;
    private CheckBox blade3;
    private TextView blade3Qty;
    private EditText blade3Comment;
    private CheckBox blade2;
    private TextView blade2Qty;
    private EditText blade2Comment;
    private CheckBox blade1;
    private TextView blade1Qty;
    private EditText blade1Comment;
    private CheckBox blade0;
    private TextView blade0Qty;
    private EditText blade0Comment;
    private CheckBox et25;
    private TextView et25Qty;
    private EditText et25Comment;
    private CheckBox et3;
    private TextView et3Qty;
    private EditText et3Comment;
    private CheckBox et35;
    private TextView et35Qty;
    private EditText et35Comment;
    private CheckBox et4;
    private TextView et4Qty;
    private EditText et4Comment;
    private CheckBox et45;
    private TextView et45Qty;
    private EditText et45Comment;
    private CheckBox et5;
    private TextView et5Qty;
    private EditText et5Comment;
    private CheckBox et55;
    private TextView et55Qty;
    private EditText et55Comment;
    private CheckBox et6;
    private TextView et6Qty;
    private EditText et6Comment;
    private CheckBox et65;
    private TextView et65Qty;
    private EditText et65Comment;
    private CheckBox et7;
    private TextView et7Qty;
    private EditText et7Comment;
    private CheckBox et75;
    private TextView et75Qty;
    private EditText et75Comment;
    private CheckBox et8;
    private TextView et8Qty;
    private EditText et8Comment;
    private CheckBox et85;
    private TextView et85Qty;
    private EditText et85Comment;
    private CheckBox tongue;
    private TextView tongueQty;
    private EditText tongueComment;
    private CheckBox childHolder;
    private TextView childHolderQty;
    private EditText childHolderComment;
    private CheckBox tubeTape;
    private TextView tubeTapeQty;
    private EditText tubeTapeComment;
    private CheckBox spareBatteries;
    private TextView spareBatteriesQty;
    private EditText spareBatteriesComment;
    private CheckBox micropore;
    private TextView microporeQty;
    private EditText microporeComment;
    private CheckBox peepValve;
    private TextView peepValveQty;
    private EditText peepValveComment;
    private CheckBox spaceBlanket;
    private TextView spaceBlanketQty;
    private EditText spaceBlanketComment;
    private CheckBox gauze;
    private TextView gauzeQty;
    private EditText gauzeComment;
    private CheckBox triBandages;
    private TextView triBandageseQty;
    private EditText triBandagesComment;
    private CheckBox vommit;
    private TextView vommitQty;
    private EditText vommitComment;
    private LinearLayout lastlin;
    private CheckBox multiPack;
    private TextView multiPackQty;
    private EditText multiPackComment;
    private Button next;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-07-03 19:13:52 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        air = (CheckBox)findViewById( R.id.air );
        antennae = (CheckBox)findViewById( R.id.antennae );
        battery = (CheckBox)findViewById( R.id.battery );
        body = (CheckBox)findViewById( R.id.body );
        brake = (CheckBox)findViewById( R.id.brake );
        branding = (CheckBox)findViewById( R.id.branding );
        dashboard = (CheckBox)findViewById( R.id.dashboard );
        emergency = (CheckBox)findViewById( R.id.emergency );
        exhaust = (CheckBox)findViewById( R.id.exhaust );
        oil = (CheckBox)findViewById( R.id.oil );
        fuel = (CheckBox)findViewById( R.id.fuel );
        headlights = (CheckBox)findViewById( R.id.headlights );
        leftIndicator = (CheckBox)findViewById( R.id.leftIndicator );
        rightIndicator = (CheckBox)findViewById( R.id.rightIndicator );
        back = (CheckBox)findViewById( R.id.back );
        front = (CheckBox)findViewById( R.id.front );
        jack = (CheckBox)findViewById( R.id.jack );
        leds = (CheckBox)findViewById( R.id.leds );
        licence = (CheckBox)findViewById( R.id.licence );
        plates = (CheckBox)findViewById( R.id.plates );
        radio = (CheckBox)findViewById( R.id.radio );
        rear = (CheckBox)findViewById( R.id.rear );
        reverse = (CheckBox)findViewById( R.id.reverse );
        side = (CheckBox)findViewById( R.id.side );
        siren = (CheckBox)findViewById( R.id.siren );
        spare = (CheckBox)findViewById( R.id.spare );
        tread = (CheckBox)findViewById( R.id.tread );
        pressure = (CheckBox)findViewById( R.id.pressure );
        windows = (CheckBox)findViewById( R.id.windows );
        windscreen = (CheckBox)findViewById( R.id.windscreen );
        airComment = (EditText)findViewById( R.id.airComment );
        antennaeComment = (EditText)findViewById( R.id.antennaeComment );
        batteryComment = (EditText)findViewById( R.id.batteryComment );
        bodyComment = (EditText)findViewById( R.id.bodyComment );
        brakeComment = (EditText)findViewById( R.id.brakeComment );
        brandingComment = (EditText)findViewById( R.id.brandingComment );
        dashboardComment = (EditText)findViewById( R.id.dashboardComment );
        emergencyComment = (EditText)findViewById( R.id.emergencyComment );
        exhaustComment = (EditText)findViewById( R.id.exhaustComment );
        oilComment = (EditText)findViewById( R.id.oilComment );
        fuelComment = (EditText)findViewById( R.id.fuelComment );
        headlightsComment = (EditText)findViewById( R.id.headlightsComment );
        leftIndicatorComment = (EditText)findViewById( R.id.leftIndicatorComment );
        rightIndicatorComment = (EditText)findViewById( R.id.rightIndicatorComment );
        backComment = (EditText)findViewById( R.id.backComment );
        frontComment = (EditText)findViewById( R.id.frontComment );
        jackComment = (EditText)findViewById( R.id.jackComment );
        ledsComment = (EditText)findViewById( R.id.ledsComment );
        licenceComment = (EditText)findViewById( R.id.licenceComment );
        platesComment = (EditText)findViewById( R.id.platesComment );
        radioComment = (EditText)findViewById( R.id.radioComment );
        rearComment = (EditText)findViewById( R.id.rearComment );
        reverseComment = (EditText)findViewById( R.id.reverseComment );
        sideComment = (EditText)findViewById( R.id.sideComment );
        sirenComment = (EditText)findViewById( R.id.sirenComment );
        spareComment = (EditText)findViewById( R.id.spareComment );
        treadComment = (EditText)findViewById( R.id.treadComment );
        pressureComment = (EditText)findViewById( R.id.pressureComment );
        windowsComment = (EditText)findViewById( R.id.windowsComment );
        windscreenComment = (EditText)findViewById( R.id.windscreenComment );
        ecg = (CheckBox)findViewById( R.id.ecg );
        ecgQty = (TextView)findViewById( R.id.ecgQty );
        ecgComment = (EditText)findViewById( R.id.ecgComment );
        adultEcg = (CheckBox)findViewById( R.id.adultEcg );
        adultEcgQty = (TextView)findViewById( R.id.adultEcgQty );
        adultEcgComment = (EditText)findViewById( R.id.adultEcgComment );
        childEcg = (CheckBox)findViewById( R.id.childEcg );
        childEcgQty = (TextView)findViewById( R.id.childEcgQty );
        childEcgComment = (EditText)findViewById( R.id.childEcgComment );
        spareBattery = (CheckBox)findViewById( R.id.spareBattery );
        spareBatteryQty = (TextView)findViewById( R.id.spareBatteryQty );
        spareBatteryComment = (EditText)findViewById( R.id.spareBatteryComment );
        spareEcg = (CheckBox)findViewById( R.id.spareEcg );
        spareEcgQty = (TextView)findViewById( R.id.spareEcgQty );
        spareEcgComment = (EditText)findViewById( R.id.spareEcgComment );
        oximeter = (CheckBox)findViewById( R.id.oximeter );
        oximeterQty = (TextView)findViewById( R.id.oximeterQty );
        oximeterComment = (EditText)findViewById( R.id.oximeterComment );
        suctionUnit = (CheckBox)findViewById( R.id.suctionUnit );
        suctionUnitQty = (TextView)findViewById( R.id.suctionUnitQty );
        suctionUnitComment = (EditText)findViewById( R.id.suctionUnitComment );
        suctionReservoir = (CheckBox)findViewById( R.id.suctionReservoir );
        suctionReservoirQty = (TextView)findViewById( R.id.suctionReservoirQty );
        suctionReservoirComment = (EditText)findViewById( R.id.suctionReservoirComment );
        chargingCable = (CheckBox)findViewById( R.id.chargingCable );
        chargingCableQty = (TextView)findViewById( R.id.chargingCableQty );
        chargingCableComment = (EditText)findViewById( R.id.chargingCableComment );
        suctionTubing = (CheckBox)findViewById( R.id.suctionTubing );
        suctionTubingQty = (TextView)findViewById( R.id.suctionTubingQty );
        suctionTubingComment = (EditText)findViewById( R.id.suctionTubingComment );
        softSuction = (CheckBox)findViewById( R.id.softSuction );
        softSuctionQty = (TextView)findViewById( R.id.softSuctionQty );
        softSuctionComment = (EditText)findViewById( R.id.softSuctionComment );
        adultSuction = (CheckBox)findViewById( R.id.adultSuction );
        adultSuctionQty = (TextView)findViewById( R.id.adultSuctionQty );
        adultSuctionComment = (EditText)findViewById( R.id.adultSuctionComment );
        paedSuction = (CheckBox)findViewById( R.id.paedSuction );
        paedSuctionQty = (TextView)findViewById( R.id.paedSuctionQty );
        paedSuctionComment = (EditText)findViewById( R.id.paedSuctionComment );
        ventilatorKit = (CheckBox)findViewById( R.id.ventilatorKit );
        ventilatorKitQty = (TextView)findViewById( R.id.ventilatorKitQty );
        ventilatorKitComment = (EditText)findViewById( R.id.ventilatorKitComment );
        circuitHose = (CheckBox)findViewById( R.id.circuitHose );
        circuitHoseQty = (TextView)findViewById( R.id.circuitHoseQty );
        circuitHoseComment = (EditText)findViewById( R.id.circuitHoseComment );
        circuitHoseFilter = (CheckBox)findViewById( R.id.circuitHoseFilter );
        circuitHoseFilterQty = (TextView)findViewById( R.id.circuitHoseFilterQty );
        circuitHoseFilterComment = (EditText)findViewById( R.id.circuitHoseFilterComment );
        co2Attachment = (CheckBox)findViewById( R.id.co2Attachment );
        co2AttachmentQty = (TextView)findViewById( R.id.co2AttachmentQty );
        co2AttachmentComment = (EditText)findViewById( R.id.co2AttachmentComment );
        oxygenCylinder = (CheckBox)findViewById( R.id.oxygenCylinder );
        oxygenCylinderQty = (TextView)findViewById( R.id.oxygenCylinderQty );
        oxygenCylinderComment = (EditText)findViewById( R.id.oxygenCylinderComment );
        oxygenGauge = (CheckBox)findViewById( R.id.oxygenGauge );
        oxygenGaugeQty = (TextView)findViewById( R.id.oxygenGaugeQty );
        oxygenGaugeComment = (EditText)findViewById( R.id.oxygenGaugeComment );
        oxygenCylinderKey = (CheckBox)findViewById( R.id.oxygenCylinderKey );
        oxygenCylinderKeyQty = (TextView)findViewById( R.id.oxygenCylinderKeyQty );
        oxygenCylinderKeyComment = (EditText)findViewById( R.id.oxygenCylinderKeyComment );
        syringePump = (CheckBox)findViewById( R.id.syringePump );
        syringePumpQty = (TextView)findViewById( R.id.syringePumpQty );
        syringePumpComment = (EditText)findViewById( R.id.syringePumpComment );
        powerCable = (CheckBox)findViewById( R.id.powerCable );
        powerCableQty = (TextView)findViewById( R.id.powerCableQty );
        powerCableComment = (EditText)findViewById( R.id.powerCableComment );
        fiftySyringe = (CheckBox)findViewById( R.id.fiftySyringe );
        fiftySyringeQty = (TextView)findViewById( R.id.fiftySyringeQty );
        fiftySyringeComment = (EditText)findViewById( R.id.fiftySyringeComment );
        microbore = (CheckBox)findViewById( R.id.microbore );
        microboreQty = (TextView)findViewById( R.id.microboreQty );
        microboreComment = (EditText)findViewById( R.id.microboreComment );
        stretcher = (CheckBox)findViewById( R.id.stretcher );
        stretcherQty = (TextView)findViewById( R.id.stretcherQty );
        stretcherComment = (EditText)findViewById( R.id.stretcherComment );
        straps = (CheckBox)findViewById( R.id.straps );
        strapsQty = (TextView)findViewById( R.id.strapsQty );
        strapsComment = (EditText)findViewById( R.id.strapsComment );
        mattress = (CheckBox)findViewById( R.id.mattress );
        mattressQty = (TextView)findViewById( R.id.mattressQty );
        mattressComment = (EditText)findViewById( R.id.mattressComment );
        sheet = (CheckBox)findViewById( R.id.sheet );
        sheetQty = (TextView)findViewById( R.id.sheetQty );
        sheetComment = (EditText)findViewById( R.id.sheetComment );
        pillow = (CheckBox)findViewById( R.id.pillow );
        pillowQty = (TextView)findViewById( R.id.pillowQty );
        pillowComment = (EditText)findViewById( R.id.pillowComment );
        pillowCase = (CheckBox)findViewById( R.id.pillowCase );
        pillowCaseQty = (TextView)findViewById( R.id.pillowCaseQty );
        pillowCaseComment = (EditText)findViewById( R.id.pillowCaseComment );
        blanket = (CheckBox)findViewById( R.id.blanket );
        blanketQty = (TextView)findViewById( R.id.blanketQty );
        blanketComment = (EditText)findViewById( R.id.blanketComment );
        other = (CheckBox)findViewById( R.id.other );
        otherQty = (TextView)findViewById( R.id.otherQty );
        otherComment = (EditText)findViewById( R.id.otherComment );
        adultKed = (CheckBox)findViewById( R.id.adultKed );
        adultKedQty = (TextView)findViewById( R.id.adultKedQty );
        adultKedComment = (EditText)findViewById( R.id.adultKedComment );
        childKed = (CheckBox)findViewById( R.id.childKed );
        childKedQty = (TextView)findViewById( R.id.childKedQty );
        childKedComment = (EditText)findViewById( R.id.childKedComment );
        adultSplint = (CheckBox)findViewById( R.id.adultSplint );
        adultSplintQty = (TextView)findViewById( R.id.adultSplintQty );
        adultSplintComment = (EditText)findViewById( R.id.adultSplintComment );
        scoopStretcher = (CheckBox)findViewById( R.id.scoopStretcher );
        scoopStretcherQty = (TextView)findViewById( R.id.scoopStretcherQty );
        scoopStretcherComment = (EditText)findViewById( R.id.scoopStretcherComment );
        spinalBoard = (CheckBox)findViewById( R.id.spinalBoard );
        spinalBoardQty = (TextView)findViewById( R.id.spinalBoardQty );
        spinalBoardComment = (EditText)findViewById( R.id.spinalBoardComment );
        headBlocks = (CheckBox)findViewById( R.id.headBlocks );
        headBlocksQty = (TextView)findViewById( R.id.headBlocksQty );
        headBlocksComment = (EditText)findViewById( R.id.headBlocksComment );
        basePlate = (CheckBox)findViewById( R.id.basePlate );
        basePlateQty = (TextView)findViewById( R.id.basePlateQty );
        basePlateComment = (EditText)findViewById( R.id.basePlateComment );
        spiderHarness = (CheckBox)findViewById( R.id.spiderHarness );
        spiderHarnessQty = (TextView)findViewById( R.id.spiderHarnessQty );
        spiderHarnessComment = (EditText)findViewById( R.id.spiderHarnessComment );
        headStraps = (CheckBox)findViewById( R.id.headStraps );
        headStrapsQty = (TextView)findViewById( R.id.headStrapsQty );
        headStrapsComment = (EditText)findViewById( R.id.headStrapsComment );
        zipStretcher = (CheckBox)findViewById( R.id.zipStretcher );
        zipStretcherBoardQty = (TextView)findViewById( R.id.zipStretcherBoardQty );
        zipStretcherComment = (EditText)findViewById( R.id.zipStretcherComment );
        longSplints = (CheckBox)findViewById( R.id.longSplints );
        longSplintsQty = (TextView)findViewById( R.id.longSplintsQty );
        longSplintsComment = (EditText)findViewById( R.id.longSplintsComment );
        shortSplints = (CheckBox)findViewById( R.id.shortSplints );
        shortSplintsQty = (TextView)findViewById( R.id.shortSplintsQty );
        shortSplintsComment = (EditText)findViewById( R.id.shortSplintsComment );
        adultCollar = (CheckBox)findViewById( R.id.adultCollar );
        adultCollarQty = (TextView)findViewById( R.id.adultCollarQty );
        adultCollarComment = (EditText)findViewById( R.id.adultCollarComment );
        childCollar = (CheckBox)findViewById( R.id.childCollar );
        childCollarQty = (TextView)findViewById( R.id.childCollarQty );
        childCollarComment = (EditText)findViewById( R.id.childCollarComment );
        fireExtinguisher = (CheckBox)findViewById( R.id.fireExtinguisher );
        fireExtinguisherQty = (TextView)findViewById( R.id.fireExtinguisherQty );
        fireExtinguisherComment = (EditText)findViewById( R.id.fireExtinguisherComment );
        rescueHelmet = (CheckBox)findViewById( R.id.rescueHelmet );
        rescueHelmetQty = (TextView)findViewById( R.id.rescueHelmetQty );
        rescueHelmetComment = (EditText)findViewById( R.id.rescueHelmetComment );
        roadCones = (CheckBox)findViewById( R.id.roadCones );
        roadConesQty = (TextView)findViewById( R.id.roadConesQty );
        roadConesComment = (EditText)findViewById( R.id.roadConesComment );
        reflectorVests = (CheckBox)findViewById( R.id.reflectorVests );
        reflectorVestsQty = (TextView)findViewById( R.id.reflectorVestsQty );
        reflectorVestsComment = (EditText)findViewById( R.id.reflectorVestsComment );
        wasteBin = (CheckBox)findViewById( R.id.wasteBin );
        wasteBinQty = (TextView)findViewById( R.id.wasteBinQty );
        wasteBinComment = (EditText)findViewById( R.id.wasteBinComment );
        medicalWaste = (CheckBox)findViewById( R.id.medicalWaste );
        medicalWasteQty = (TextView)findViewById( R.id.medicalWasteQty );
        medicalWasteComment = (EditText)findViewById( R.id.medicalWasteComment );
        sharpsBin = (CheckBox)findViewById( R.id.sharpsBin );
        sharpsBinQty = (TextView)findViewById( R.id.sharpsBinQty );
        sharpsBinComment = (EditText)findViewById( R.id.sharpsBinComment );
        bodyBag = (CheckBox)findViewById( R.id.bodyBag );
        bodyBagQty = (TextView)findViewById( R.id.bodyBagQty );
        bodyBagComment = (EditText)findViewById( R.id.bodyBagComment );
        gloves = (CheckBox)findViewById( R.id.gloves );
        glovesQty = (TextView)findViewById( R.id.glovesQty );
        glovesComment = (EditText)findViewById( R.id.glovesComment );
        portableOxygen = (CheckBox)findViewById( R.id.portableOxygen );
        portableOxygenQty = (TextView)findViewById( R.id.portableOxygenQty );
        portableOxygenComment = (EditText)findViewById( R.id.portableOxygenComment );
        pinRegulator = (CheckBox)findViewById( R.id.pinRegulator );
        pinRegulatorQty = (TextView)findViewById( R.id.pinRegulatorQty );
        pinRegulatorComment = (EditText)findViewById( R.id.pinRegulatorComment );
        oxygenMainline = (CheckBox)findViewById( R.id.oxygenMainline );
        oxygenMainlineQty = (TextView)findViewById( R.id.oxygenMainlineQty );
        oxygenMainlineComment = (EditText)findViewById( R.id.oxygenMainlineComment );
        oxygenFlowMeter = (CheckBox)findViewById( R.id.oxygenFlowMeter );
        oxygenFlowMeterQty = (TextView)findViewById( R.id.oxygenFlowMeterQty );
        oxygenFlowMeterComment = (EditText)findViewById( R.id.oxygenFlowMeterComment );
        bullNose = (CheckBox)findViewById( R.id.bullNose );
        bullNoseQty = (TextView)findViewById( R.id.bullNoseQty );
        bullNoseComment = (EditText)findViewById( R.id.bullNoseComment );
        aed = (CheckBox)findViewById( R.id.aed );
        aedQty = (TextView)findViewById( R.id.aedQty );
        aedComment = (EditText)findViewById( R.id.aedComment );
        adultDefibPads = (CheckBox)findViewById( R.id.adultDefibPads );
        adultDefibPadsQty = (TextView)findViewById( R.id.adultDefibPadsQty );
        adultDefibPadsComment = (EditText)findViewById( R.id.adultDefibPadsComment );
        childDefibPads = (CheckBox)findViewById( R.id.childDefibPads );
        childDefibPadsQty = (TextView)findViewById( R.id.childDefibPadsQty );
        childDefibPadsComment = (EditText)findViewById( R.id.childDefibPadsComment );
        defibGel = (CheckBox)findViewById( R.id.defibGel );
        defibGelQty = (TextView)findViewById( R.id.defibGelQty );
        defibGelComment = (EditText)findViewById( R.id.defibGelComment );
        asprin = (CheckBox)findViewById( R.id.asprin );
        asprinQty = (TextView)findViewById( R.id.asprinQty );
        asprinComment = (EditText)findViewById( R.id.asprinComment );
        gtn = (CheckBox)findViewById( R.id.gtn );
        gtnQty = (TextView)findViewById( R.id.gtnQty );
        gtnComment = (EditText)findViewById( R.id.gtnComment );
        clopidogrel = (CheckBox)findViewById( R.id.clopidogrel );
        clopidogrelQty = (TextView)findViewById( R.id.clopidogrelQty );
        clopidogrelComment = (EditText)findViewById( R.id.clopidogrelComment );
        adenosine = (CheckBox)findViewById( R.id.adenosine );
        adenosineQty = (TextView)findViewById( R.id.adenosineQty );
        adenosineComment = (EditText)findViewById( R.id.adenosineComment );
        amiodarone = (CheckBox)findViewById( R.id.amiodarone );
        amiodaroneQty = (TextView)findViewById( R.id.amiodaroneQty );
        amiodaroneComment = (EditText)findViewById( R.id.amiodaroneComment );
        lignocaine = (CheckBox)findViewById( R.id.lignocaine );
        lignocaineQty = (TextView)findViewById( R.id.lignocaineQty );
        lignocaineComment = (EditText)findViewById( R.id.lignocaineComment );
        adrenaline = (CheckBox)findViewById( R.id.adrenaline );
        adrenalineQty = (TextView)findViewById( R.id.adrenalineQty );
        adrenalineComment = (EditText)findViewById( R.id.adrenalineComment );
        atropine = (CheckBox)findViewById( R.id.atropine );
        atropineQty = (TextView)findViewById( R.id.atropineQty );
        atropineComment = (EditText)findViewById( R.id.atropineComment );
        magnesium = (CheckBox)findViewById( R.id.magnesium );
        magnesiumQty = (TextView)findViewById( R.id.magnesiumQty );
        magnesiumComment = (EditText)findViewById( R.id.magnesiumComment );
        calcium = (CheckBox)findViewById( R.id.calcium );
        calciumQty = (TextView)findViewById( R.id.calciumQty );
        calciumComment = (EditText)findViewById( R.id.calciumComment );
        soda = (CheckBox)findViewById( R.id.soda );
        sodaQty = (TextView)findViewById( R.id.sodaQty );
        sodaComment = (EditText)findViewById( R.id.sodaComment );
        thiamine = (CheckBox)findViewById( R.id.thiamine );
        thiamineQty = (TextView)findViewById( R.id.thiamineQty );
        thiamineComment = (EditText)findViewById( R.id.thiamineComment );
        diazepam = (CheckBox)findViewById( R.id.diazepam );
        diazepamQty = (TextView)findViewById( R.id.diazepamQty );
        diazepamComment = (EditText)findViewById( R.id.diazepamComment );
        midazolam = (CheckBox)findViewById( R.id.midazolam );
        midazolamQty = (TextView)findViewById( R.id.midazolamQty );
        midazolamComment = (EditText)findViewById( R.id.midazolamComment );
        promethazine = (CheckBox)findViewById( R.id.promethazine );
        promethazineQty = (TextView)findViewById( R.id.promethazineQty );
        promethazineComment = (EditText)findViewById( R.id.promethazineComment );
        activatedCharcoal = (CheckBox)findViewById( R.id.activatedCharcoal );
        activatedCharcoalQty = (TextView)findViewById( R.id.activatedCharcoalQty );
        activatedCharcoalComment = (EditText)findViewById( R.id.activatedCharcoalComment );
        flumazenil = (CheckBox)findViewById( R.id.flumazenil );
        flumazenilQty = (TextView)findViewById( R.id.flumazenilQty );
        flumazenilComment = (EditText)findViewById( R.id.flumazenilComment );
        naloxone = (CheckBox)findViewById( R.id.naloxone );
        naloxoneQty = (TextView)findViewById( R.id.naloxoneQty );
        naloxoneComment = (EditText)findViewById( R.id.naloxoneComment );
        morphine = (CheckBox)findViewById( R.id.morphine );
        morphineQty = (TextView)findViewById( R.id.morphineQty );
        morphineComment = (EditText)findViewById( R.id.morphineComment );
        dextrose = (CheckBox)findViewById( R.id.dextrose );
        dextroseQty = (TextView)findViewById( R.id.dextroseQty );
        dextroseComment = (EditText)findViewById( R.id.dextroseComment );
        glucose = (CheckBox)findViewById( R.id.glucose );
        glucoseQty = (TextView)findViewById( R.id.glucoseQty );
        glucoseComment = (EditText)findViewById( R.id.glucoseComment );
        fenoterol = (CheckBox)findViewById( R.id.fenoterol );
        fenoterolQty = (TextView)findViewById( R.id.fenoterolQty );
        fenoterolComment = (EditText)findViewById( R.id.fenoterolComment );
        bromide = (CheckBox)findViewById( R.id.bromide );
        bromideQty = (TextView)findViewById( R.id.bromideQty );
        bromideComment = (EditText)findViewById( R.id.bromideComment );
        corticosteriods = (CheckBox)findViewById( R.id.corticosteriods );
        corticosteriodsQty = (TextView)findViewById( R.id.corticosteriodsQty );
        corticosteriodsComment = (EditText)findViewById( R.id.corticosteriodsComment );
        furosemide = (CheckBox)findViewById( R.id.furosemide );
        furosemideQty = (TextView)findViewById( R.id.furosemideQty );
        furosemideComment = (EditText)findViewById( R.id.furosemideComment );
        metaclopramide = (CheckBox)findViewById( R.id.metaclopramide );
        metaclopramideQty = (TextView)findViewById( R.id.metaclopramideQty );
        metaclopramideComment = (EditText)findViewById( R.id.metaclopramideComment );
        buscopan = (CheckBox)findViewById( R.id.buscopan );
        buscopanQty = (TextView)findViewById( R.id.buscopanQty );
        buscopanComment = (EditText)findViewById( R.id.buscopanComment );
        bigPlasters = (CheckBox)findViewById( R.id.bigPlasters );
        bigPlastersQty = (TextView)findViewById( R.id.bigPlastersQty );
        bigPlastersComment = (EditText)findViewById( R.id.bigPlastersComment );
        smallPlasters = (CheckBox)findViewById( R.id.smallPlasters );
        smallPlastersQty = (TextView)findViewById( R.id.smallPlastersQty );
        smallPlastersComment = (EditText)findViewById( R.id.smallPlastersComment );
        cetrimide = (CheckBox)findViewById( R.id.cetrimide );
        cetrimideQty = (TextView)findViewById( R.id.cetrimideQty );
        cetrimideComment = (EditText)findViewById( R.id.cetrimideComment );
        elastoplastRoll = (CheckBox)findViewById( R.id.elastoplastRoll );
        elastoplastRollQty = (TextView)findViewById( R.id.elastoplastRollQty );
        elastoplastRollComment = (EditText)findViewById( R.id.elastoplastRollComment );
        prf = (CheckBox)findViewById( R.id.prf );
        prfQty = (TextView)findViewById( R.id.prfQty );
        prfComment = (EditText)findViewById( R.id.prfComment );
        doa = (CheckBox)findViewById( R.id.doa );
        doaQty = (TextView)findViewById( R.id.doaQty );
        doaComment = (EditText)findViewById( R.id.doaComment );
        gop = (CheckBox)findViewById( R.id.gop );
        gopQty = (TextView)findViewById( R.id.gopQty );
        gopComment = (EditText)findViewById( R.id.gopComment );
        torniquet = (CheckBox)findViewById( R.id.torniquet );
        torniquetQty = (TextView)findViewById( R.id.torniquetQty );
        torniquetComment = (EditText)findViewById( R.id.torniquetComment );
        glucometer = (CheckBox)findViewById( R.id.glucometer );
        glucometerQty = (TextView)findViewById( R.id.glucometerQty );
        glucometerComment = (EditText)findViewById( R.id.glucometerComment );
        gloves2 = (CheckBox)findViewById( R.id.gloves2 );
        gloves2Qty = (TextView)findViewById( R.id.gloves2Qty );
        gloves2Comment = (EditText)findViewById( R.id.gloves2Comment );
        bp = (CheckBox)findViewById( R.id.bp );
        bpQty = (TextView)findViewById( R.id.bpQty );
        bpComment = (EditText)findViewById( R.id.bpComment );
        stethoscope = (CheckBox)findViewById( R.id.stethoscope );
        stethoscopeQty = (TextView)findViewById( R.id.stethoscopeQty );
        stethoscopeComment = (EditText)findViewById( R.id.stethoscopeComment );
        rescueScissors = (CheckBox)findViewById( R.id.rescueScissors );
        rescueScissorsQty = (TextView)findViewById( R.id.rescueScissorsQty );
        rescueScissorsComment = (EditText)findViewById( R.id.rescueScissorsComment );
        thermometer = (CheckBox)findViewById( R.id.thermometer );
        thermometerQty = (TextView)findViewById( R.id.thermometerQty );
        thermometerComment = (EditText)findViewById( R.id.thermometerComment );
        pupilTorch = (CheckBox)findViewById( R.id.pupilTorch );
        pupilTorchQty = (TextView)findViewById( R.id.pupilTorchQty );
        pupilTorchComment = (EditText)findViewById( R.id.pupilTorchComment );
        testStrips = (CheckBox)findViewById( R.id.testStrips );
        testStripsQty = (TextView)findViewById( R.id.testStripsQty );
        testStripsComment = (EditText)findViewById( R.id.testStripsComment );
        lancets = (CheckBox)findViewById( R.id.lancets );
        lancetsQty = (TextView)findViewById( R.id.lancetsQty );
        lancetsComment = (EditText)findViewById( R.id.lancetsComment );
        sats = (CheckBox)findViewById( R.id.sats );
        satsQty = (TextView)findViewById( R.id.satsQty );
        satsComment = (EditText)findViewById( R.id.satsComment );
        adultMask = (CheckBox)findViewById( R.id.adultMask );
        adultMaskQty = (TextView)findViewById( R.id.adultMaskQty );
        adultMaskComment = (EditText)findViewById( R.id.adultMaskComment );
        adultRebreather = (CheckBox)findViewById( R.id.adultRebreather );
        adultRebreatherQty = (TextView)findViewById( R.id.adultRebreatherQty );
        adultRebreatherComment = (EditText)findViewById( R.id.adultRebreatherComment );
        adultNebulizer = (CheckBox)findViewById( R.id.adultNebulizer );
        adultNebulizerQty = (TextView)findViewById( R.id.adultNebulizerQty );
        adultNebulizerComment = (EditText)findViewById( R.id.adultNebulizerComment );
        adultNasal = (CheckBox)findViewById( R.id.adultNasal );
        adultNasalQty = (TextView)findViewById( R.id.adultNasalQty );
        adultNasalComment = (EditText)findViewById( R.id.adultNasalComment );
        childMask = (CheckBox)findViewById( R.id.childMask );
        childMaskQty = (TextView)findViewById( R.id.childMaskQty );
        childMaskComment = (EditText)findViewById( R.id.childMaskComment );
        childRebreather = (CheckBox)findViewById( R.id.childRebreather );
        childRebreatherQty = (TextView)findViewById( R.id.childRebreatherQty );
        childRebreatherComment = (EditText)findViewById( R.id.childRebreatherComment );
        childNebuliser = (CheckBox)findViewById( R.id.childNebuliser );
        childNebuliserQty = (TextView)findViewById( R.id.childNebuliserQty );
        childNebuliserComment = (EditText)findViewById( R.id.childNebuliserComment );
        childNasal = (CheckBox)findViewById( R.id.childNasal );
        childNasalQty = (TextView)findViewById( R.id.childNasalQty );
        childNasalComment = (EditText)findViewById( R.id.childNasalComment );
        traumaPads = (CheckBox)findViewById( R.id.traumaPads );
        traumaPadsQty = (TextView)findViewById( R.id.traumaPadsQty );
        traumaPadsComment = (EditText)findViewById( R.id.traumaPadsComment );
        traumaDressing100 = (CheckBox)findViewById( R.id.traumaDressing100 );
        traumaDressing100Qty = (TextView)findViewById( R.id.traumaDressing100Qty );
        traumaDressing100Comment = (EditText)findViewById( R.id.traumaDressing100Comment );
        traumaDressing200 = (CheckBox)findViewById( R.id.traumaDressing200 );
        traumaDressing200Qty = (TextView)findViewById( R.id.traumaDressing200Qty );
        traumaDressing200Comment = (EditText)findViewById( R.id.traumaDressing200Comment );
        bandage75 = (CheckBox)findViewById( R.id.bandage75 );
        bandage75Qty = (TextView)findViewById( R.id.bandage75Qty );
        bandage75Comment = (EditText)findViewById( R.id.bandage75Comment );
        bandage50 = (CheckBox)findViewById( R.id.bandage50 );
        bandage50Qty = (TextView)findViewById( R.id.bandage50Qty );
        bandage50Comment = (EditText)findViewById( R.id.bandage50Comment );
        stretchBandage = (CheckBox)findViewById( R.id.stretchBandage );
        stretchBandageQty = (TextView)findViewById( R.id.stretchBandageQty );
        stretchBandageComment = (EditText)findViewById( R.id.stretchBandageComment );
        elastoplast = (CheckBox)findViewById( R.id.elastoplast );
        elastoplastQty = (TextView)findViewById( R.id.elastoplastQty );
        elastoplastComment = (EditText)findViewById( R.id.elastoplastComment );
        adultBvm = (CheckBox)findViewById( R.id.adultBvm );
        adultBvmQty = (TextView)findViewById( R.id.adultBvmQty );
        adultBvmComment = (EditText)findViewById( R.id.adultBvmComment );
        childBvm = (CheckBox)findViewById( R.id.childBvm );
        childBvmQty = (TextView)findViewById( R.id.childBvmQty );
        childBvmComment = (EditText)findViewById( R.id.childBvmComment );
        infantBvm = (CheckBox)findViewById( R.id.infantBvm );
        infantBvmQty = (TextView)findViewById( R.id.infantBvmQty );
        infantBvmComment = (EditText)findViewById( R.id.infantBvmComment );
        adultMagilles = (CheckBox)findViewById( R.id.adultMagilles );
        adultMagillesQty = (TextView)findViewById( R.id.adultMagillesQty );
        adultMagillesComment = (EditText)findViewById( R.id.adultMagillesComment );
        childMagilles = (CheckBox)findViewById( R.id.childMagilles );
        childMagillesQty = (TextView)findViewById( R.id.childMagillesQty );
        childMagillesComment = (EditText)findViewById( R.id.childMagillesComment );
        tube000 = (CheckBox)findViewById( R.id.tube000 );
        tube000Qty = (TextView)findViewById( R.id.tube000Qty );
        tube000Comment = (EditText)findViewById( R.id.tube000Comment );
        tube00 = (CheckBox)findViewById( R.id.tube00 );
        tube00Qty = (TextView)findViewById( R.id.tube00Qty );
        tube00Comment = (EditText)findViewById( R.id.tube00Comment );
        tube0 = (CheckBox)findViewById( R.id.tube0 );
        tube0Qty = (TextView)findViewById( R.id.tube0Qty );
        tube0Comment = (EditText)findViewById( R.id.tube0Comment );
        tube1 = (CheckBox)findViewById( R.id.tube1 );
        tube1Qty = (TextView)findViewById( R.id.tube1Qty );
        tube1Comment = (EditText)findViewById( R.id.tube1Comment );
        tube2 = (CheckBox)findViewById( R.id.tube2 );
        tube2Qty = (TextView)findViewById( R.id.tube2Qty );
        tube2Comment = (EditText)findViewById( R.id.tube2Comment );
        tube3 = (CheckBox)findViewById( R.id.tube3 );
        tube3Qty = (TextView)findViewById( R.id.tube3Qty );
        tube3Comment = (EditText)findViewById( R.id.tube3Comment );
        tube4 = (CheckBox)findViewById( R.id.tube4 );
        tube4Qty = (TextView)findViewById( R.id.tube4Qty );
        tube4Comment = (EditText)findViewById( R.id.tube4Comment );
        tube5 = (CheckBox)findViewById( R.id.tube5 );
        tube5Qty = (TextView)findViewById( R.id.tube5Qty );
        tube5Comment = (EditText)findViewById( R.id.tube5Comment );
        lma5 = (CheckBox)findViewById( R.id.lma5 );
        lma5Qty = (TextView)findViewById( R.id.lma5Qty );
        lma5Comment = (EditText)findViewById( R.id.lma5Comment );
        lma4 = (CheckBox)findViewById( R.id.lma4 );
        lma4Qty = (TextView)findViewById( R.id.lma4Qty );
        lma4Comment = (EditText)findViewById( R.id.lma4Comment );
        lma3 = (CheckBox)findViewById( R.id.lma3 );
        lma3Qty = (TextView)findViewById( R.id.lma3Qty );
        lma3Comment = (EditText)findViewById( R.id.lma3Comment );
        lma2 = (CheckBox)findViewById( R.id.lma2 );
        lma2Qty = (TextView)findViewById( R.id.lma2Qty );
        lma2Comment = (EditText)findViewById( R.id.lma2Comment );
        lma1 = (CheckBox)findViewById( R.id.lma1 );
        lma1Qty = (TextView)findViewById( R.id.lma1Qty );
        lma1Comment = (EditText)findViewById( R.id.lma1Comment );
        uvcPack = (CheckBox)findViewById( R.id.uvcPack );
        uvcPackQty = (TextView)findViewById( R.id.uvcPackQty );
        uvcPackComment = (EditText)findViewById( R.id.uvcPackComment );
        maternity = (CheckBox)findViewById( R.id.maternity );
        maternityQty = (TextView)findViewById( R.id.maternityQty );
        maternityComment = (EditText)findViewById( R.id.maternityComment );
        needleCric = (CheckBox)findViewById( R.id.needleCric );
        needleCricQty = (TextView)findViewById( R.id.needleCricQty );
        needleCricComment = (EditText)findViewById( R.id.needleCricComment );
        ngTube = (CheckBox)findViewById( R.id.ngTube );
        ngTubeQty = (TextView)findViewById( R.id.ngTubeQty );
        ngTubeComment = (EditText)findViewById( R.id.ngTubeComment );
        airwayPack = (CheckBox)findViewById( R.id.airwayPack );
        airwayPackQty = (TextView)findViewById( R.id.airwayPackQty );
        airwayPackComment = (EditText)findViewById( R.id.airwayPackComment );
        urinaryCatheter = (CheckBox)findViewById( R.id.urinaryCatheter );
        urinaryCatheterQty = (TextView)findViewById( R.id.urinaryCatheterQty );
        urinaryCatheterComment = (EditText)findViewById( R.id.urinaryCatheterComment );
        urineBag = (CheckBox)findViewById( R.id.urineBag );
        urineBagQty = (TextView)findViewById( R.id.urineBagQty );
        urineBagComment = (EditText)findViewById( R.id.urineBagComment );
        suturePack = (CheckBox)findViewById( R.id.suturePack );
        suturePackQty = (TextView)findViewById( R.id.suturePackQty );
        suturePackComment = (EditText)findViewById( R.id.suturePackComment );
        spirometer = (CheckBox)findViewById( R.id.spirometer );
        spirometerQty = (TextView)findViewById( R.id.spirometerQty );
        spirometerComment = (EditText)findViewById( R.id.spirometerComment );
        ringers1000 = (CheckBox)findViewById( R.id.ringers1000 );
        ringers1000Qty = (TextView)findViewById( R.id.ringers1000Qty );
        ringers1000Comment = (EditText)findViewById( R.id.ringers1000Comment );
        sodiumChloride = (CheckBox)findViewById( R.id.sodiumChloride );
        sodiumChlorideQty = (TextView)findViewById( R.id.sodiumChlorideQty );
        sodiumChlorideComment = (EditText)findViewById( R.id.sodiumChlorideComment );
        colloid = (CheckBox)findViewById( R.id.colloid );
        colloidQty = (TextView)findViewById( R.id.colloidQty );
        colloidComment = (EditText)findViewById( R.id.colloidComment );
        canulla14 = (CheckBox)findViewById( R.id.canulla14 );
        canulla14Qty = (TextView)findViewById( R.id.canulla14Qty );
        canulla14Comment = (EditText)findViewById( R.id.canulla14Comment );
        canulla16 = (CheckBox)findViewById( R.id.canulla16 );
        canulla16Qty = (TextView)findViewById( R.id.canulla16Qty );
        canulla16Comment = (EditText)findViewById( R.id.canulla16Comment );
        canulla18 = (CheckBox)findViewById( R.id.canulla18 );
        canulla18Qty = (TextView)findViewById( R.id.canulla18Qty );
        canulla18Comment = (EditText)findViewById( R.id.canulla18Comment );
        canulla20 = (CheckBox)findViewById( R.id.canulla20 );
        canulla20Qty = (TextView)findViewById( R.id.canulla20Qty );
        canulla20Comment = (EditText)findViewById( R.id.canulla20Comment );
        canulla22 = (CheckBox)findViewById( R.id.canulla22 );
        canulla22Qty = (TextView)findViewById( R.id.canulla22Qty );
        canulla22Comment = (EditText)findViewById( R.id.canulla22Comment );
        canulla24 = (CheckBox)findViewById( R.id.canulla24 );
        canulla24Qty = (TextView)findViewById( R.id.canulla24Qty );
        canulla24Comment = (EditText)findViewById( R.id.canulla24Comment );
        admin60 = (CheckBox)findViewById( R.id.admin60 );
        admin60Qty = (TextView)findViewById( R.id.admin60Qty );
        admin60Comment = (EditText)findViewById( R.id.admin60Comment );
        admin20 = (CheckBox)findViewById( R.id.admin20 );
        admin20Qty = (TextView)findViewById( R.id.admin20Qty );
        admin20Comment = (EditText)findViewById( R.id.admin20Comment );
        admin15 = (CheckBox)findViewById( R.id.admin15 );
        admin15Qty = (TextView)findViewById( R.id.admin15Qty );
        admin15Comment = (EditText)findViewById( R.id.admin15Comment );
        admin10 = (CheckBox)findViewById( R.id.admin10 );
        admin10Qty = (TextView)findViewById( R.id.admin10Qty );
        admin10Comment = (EditText)findViewById( R.id.admin10Comment );
        webcol = (CheckBox)findViewById( R.id.webcol );
        webcolQty = (TextView)findViewById( R.id.webcolQty );
        webcolComment = (EditText)findViewById( R.id.webcolComment );
        tegaderm = (CheckBox)findViewById( R.id.tegaderm );
        tegadermQty = (TextView)findViewById( R.id.tegadermQty );
        tegadermComment = (EditText)findViewById( R.id.tegadermComment );
        syringe50 = (CheckBox)findViewById( R.id.syringe50 );
        syringe50Qty = (TextView)findViewById( R.id.syringe50Qty );
        syringe50Comment = (EditText)findViewById( R.id.syringe50Comment );
        syringe20 = (CheckBox)findViewById( R.id.syringe20 );
        syringe20Qty = (TextView)findViewById( R.id.syringe20Qty );
        syringe20Comment = (EditText)findViewById( R.id.syringe20Comment );
        syringe10 = (CheckBox)findViewById( R.id.syringe10 );
        syringe10Qty = (TextView)findViewById( R.id.syringe10Qty );
        syringe10Comment = (EditText)findViewById( R.id.syringe10Comment );
        syringe5 = (CheckBox)findViewById( R.id.syringe5 );
        syringe5Qty = (TextView)findViewById( R.id.syringe5Qty );
        syringe5Comment = (EditText)findViewById( R.id.syringe5Comment );
        syringe3 = (CheckBox)findViewById( R.id.syringe3 );
        syringe3Qty = (TextView)findViewById( R.id.syringe3Qty );
        syringe3Comment = (EditText)findViewById( R.id.syringe3Comment );
        needle18 = (CheckBox)findViewById( R.id.needle18 );
        needle18Qty = (TextView)findViewById( R.id.needle18Qty );
        needle18Comment = (EditText)findViewById( R.id.needle18Comment );
        needle21 = (CheckBox)findViewById( R.id.needle21 );
        needle21Qty = (TextView)findViewById( R.id.needle21Qty );
        needle21Comment = (EditText)findViewById( R.id.needle21Comment );
        handle = (CheckBox)findViewById( R.id.handle );
        handleQty = (TextView)findViewById( R.id.handleQty );
        handleComment = (EditText)findViewById( R.id.handleComment );
        blade4 = (CheckBox)findViewById( R.id.blade4 );
        blade4Qty = (TextView)findViewById( R.id.blade4Qty );
        blade4Comment = (EditText)findViewById( R.id.blade4Comment );
        blade3 = (CheckBox)findViewById( R.id.blade3 );
        blade3Qty = (TextView)findViewById( R.id.blade3Qty );
        blade3Comment = (EditText)findViewById( R.id.blade3Comment );
        blade2 = (CheckBox)findViewById( R.id.blade2 );
        blade2Qty = (TextView)findViewById( R.id.blade2Qty );
        blade2Comment = (EditText)findViewById( R.id.blade2Comment );
        blade1 = (CheckBox)findViewById( R.id.blade1 );
        blade1Qty = (TextView)findViewById( R.id.blade1Qty );
        blade1Comment = (EditText)findViewById( R.id.blade1Comment );
        blade0 = (CheckBox)findViewById( R.id.blade0 );
        blade0Qty = (TextView)findViewById( R.id.blade0Qty );
        blade0Comment = (EditText)findViewById( R.id.blade0Comment );
        et25 = (CheckBox)findViewById( R.id.et2_5 );
        et25Qty = (TextView)findViewById( R.id.et2_5Qty );
        et25Comment = (EditText)findViewById( R.id.et2_5Comment );
        et3 = (CheckBox)findViewById( R.id.et3 );
        et3Qty = (TextView)findViewById( R.id.et3Qty );
        et3Comment = (EditText)findViewById( R.id.et3Comment );
        et35 = (CheckBox)findViewById( R.id.et3_5 );
        et35Qty = (TextView)findViewById( R.id.et3_5Qty );
        et35Comment = (EditText)findViewById( R.id.et3_5Comment );
        et4 = (CheckBox)findViewById( R.id.et4 );
        et4Qty = (TextView)findViewById( R.id.et4Qty );
        et4Comment = (EditText)findViewById( R.id.et4Comment );
        et45 = (CheckBox)findViewById( R.id.et4_5 );
        et45Qty = (TextView)findViewById( R.id.et4_5Qty );
        et45Comment = (EditText)findViewById( R.id.et4_5Comment );
        et5 = (CheckBox)findViewById( R.id.et5 );
        et5Qty = (TextView)findViewById( R.id.et5Qty );
        et5Comment = (EditText)findViewById( R.id.et5Comment );
        et55 = (CheckBox)findViewById( R.id.et5_5 );
        et55Qty = (TextView)findViewById( R.id.et5_5Qty );
        et55Comment = (EditText)findViewById( R.id.et5_5Comment );
        et6 = (CheckBox)findViewById( R.id.et6 );
        et6Qty = (TextView)findViewById( R.id.et6Qty );
        et6Comment = (EditText)findViewById( R.id.et6Comment );
        et65 = (CheckBox)findViewById( R.id.et6_5 );
        et65Qty = (TextView)findViewById( R.id.et6_5Qty );
        et65Comment = (EditText)findViewById( R.id.et6_5Comment );
        et7 = (CheckBox)findViewById( R.id.et7 );
        et7Qty = (TextView)findViewById( R.id.et7Qty );
        et7Comment = (EditText)findViewById( R.id.et7Comment );
        et75 = (CheckBox)findViewById( R.id.et7_5 );
        et75Qty = (TextView)findViewById( R.id.et7_5Qty );
        et75Comment = (EditText)findViewById( R.id.et7_5Comment );
        et8 = (CheckBox)findViewById( R.id.et8 );
        et8Qty = (TextView)findViewById( R.id.et8Qty );
        et8Comment = (EditText)findViewById( R.id.et8Comment );
        et85 = (CheckBox)findViewById( R.id.et8_5 );
        et85Qty = (TextView)findViewById( R.id.et8_5Qty );
        et85Comment = (EditText)findViewById( R.id.et8_5Comment );
        tongue = (CheckBox)findViewById( R.id.tongue );
        tongueQty = (TextView)findViewById( R.id.tongueQty );
        tongueComment = (EditText)findViewById( R.id.tongueComment );
        childHolder = (CheckBox)findViewById( R.id.childHolder );
        childHolderQty = (TextView)findViewById( R.id.childHolderQty );
        childHolderComment = (EditText)findViewById( R.id.childHolderComment );
        tubeTape = (CheckBox)findViewById( R.id.tubeTape );
        tubeTapeQty = (TextView)findViewById( R.id.tubeTapeQty );
        tubeTapeComment = (EditText)findViewById( R.id.tubeTapeComment );
        spareBatteries = (CheckBox)findViewById( R.id.spareBatteries );
        spareBatteriesQty = (TextView)findViewById( R.id.spareBatteriesQty );
        spareBatteriesComment = (EditText)findViewById( R.id.spareBatteriesComment );
        micropore = (CheckBox)findViewById( R.id.micropore );
        microporeQty = (TextView)findViewById( R.id.microporeQty );
        microporeComment = (EditText)findViewById( R.id.microporeComment );
        peepValve = (CheckBox)findViewById( R.id.peepValve );
        peepValveQty = (TextView)findViewById( R.id.peepValveQty );
        peepValveComment = (EditText)findViewById( R.id.peepValveComment );
        spaceBlanket = (CheckBox)findViewById( R.id.spaceBlanket );
        spaceBlanketQty = (TextView)findViewById( R.id.spaceBlanketQty );
        spaceBlanketComment = (EditText)findViewById( R.id.spaceBlanketComment );
        gauze = (CheckBox)findViewById( R.id.gauze );
        gauzeQty = (TextView)findViewById( R.id.gauzeQty );
        gauzeComment = (EditText)findViewById( R.id.gauzeComment );
        triBandages = (CheckBox)findViewById( R.id.triBandages );
        triBandageseQty = (TextView)findViewById( R.id.triBandageseQty );
        triBandagesComment = (EditText)findViewById( R.id.triBandagesComment );
        vommit = (CheckBox)findViewById( R.id.vommit );
        vommitQty = (TextView)findViewById( R.id.vommitQty );
        vommitComment = (EditText)findViewById( R.id.vommitComment );
        lastlin = (LinearLayout)findViewById( R.id.lastlin );
        multiPack = (CheckBox)findViewById( R.id.multiPack );
        multiPackQty = (TextView)findViewById( R.id.multiPackQty );
        multiPackComment = (EditText)findViewById( R.id.multiPackComment );
        next = (Button)findViewById( R.id.next );


    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-07-03 19:13:52 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_als__icu__amb);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Bundle bundle = getIntent().getExtras();
        code = bundle.getString("code");
        cache = new Cache(getApplicationContext());
        save = new JSONObject();

        VehicleNumber = findViewById(R.id.vehicleNumberSpinner);
        dateView = findViewById(R.id.dateView);
        driver = findViewById(R.id.driverEdit);
        controller = findViewById(R.id.controllerEdit);
        checkedBy = findViewById(R.id.checkedEdit);
        inspectionTime = findViewById(R.id.timeEdit);
        dateView = findViewById(R.id.dateView);
        url = "http://capemedicstestserver-com.stackstaging.com/apktest/vehicleChecklist.php";
        driver.requestFocus();

        final String[] vehicleNumber = {"MED 1","MED 2","MED 3","MED 4","MED 5","MED 6","MED 7","MED 8","MED 9","MED 10","MED 11","MED 12","MED 13","MED 14","MED 15","MED 16","MED 17","MED 18","MED 19","MED 20","MED 21","MED 22","MED 23","MED 24"};

        ArrayAdapter<String> vehicleNumberAdapter = new ArrayAdapter<>(this,
                R.layout.custom_spinner,vehicleNumber);

        vehicleNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        VehicleNumber.setAdapter(vehicleNumberAdapter);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String DateTime = dateFormat.format(date);
        String Date = DateTime.substring(0,10);
        dateView.setText(Date);
        findViews();

        checkBoxList = Arrays.asList(air,antennae,battery,body,brake,branding,dashboard,emergency,exhaust,oil,fuel,headlights,leftIndicator,rightIndicator,back,front,jack,leds,licence,plates,radio,rear,reverse,side,siren,spare,tread,pressure,windows,windscreen,adultEcg,childEcg,spareBattery,spareEcg,oximeter,suctionUnit,suctionReservoir,chargingCable,suctionTubing,softSuction,adultSuction,paedSuction,ventilatorKit,circuitHose,circuitHoseFilter,co2Attachment,oxygenCylinder,oxygenGauge,oxygenCylinderKey,syringePump,powerCable,fiftySyringe,microbore,stretcher,straps,mattress,sheet,pillow,pillowCase,blanket,other,adultKed,childKed,adultSplint,scoopStretcher,spinalBoard,headBlocks,basePlate,spiderHarness,headStraps,zipStretcher,longSplints,shortSplints,adultCollar,childCollar,fireExtinguisher,rescueHelmet,roadCones,reflectorVests,wasteBin,medicalWaste,sharpsBin,bodyBag,gloves,portableOxygen,pinRegulator,oxygenMainline,oxygenFlowMeter,bullNose,aed,adultDefibPads,childDefibPads,defibGel,asprin,gtn,clopidogrel,adenosine,amiodarone,lignocaine,adrenaline,atropine,magnesium,calcium,soda,thiamine,diazepam,midazolam,promethazine,activatedCharcoal,flumazenil,naloxone,morphine,dextrose,glucose,fenoterol,bromide,corticosteriods,furosemide,metaclopramide,buscopan,bigPlasters,smallPlasters,cetrimide,elastoplastRoll,prf,doa,gop,torniquet,glucometer,gloves2,bp,stethoscope,rescueScissors,thermometer,pupilTorch,testStrips,lancets,sats,adultMask,adultRebreather,adultNebulizer,adultNasal,childMask,childRebreather,childNebuliser,childNasal,traumaPads,traumaDressing100,traumaDressing200,bandage75,bandage50,stretchBandage,elastoplast,adultBvm,childBvm,infantBvm,adultMagilles,childMagilles,tube000,tube00,tube0,tube1,tube2,tube3,tube4,tube5,lma5,lma4,lma3,lma2,lma1,uvcPack,maternity,needleCric,ngTube,airwayPack,urinaryCatheter,urineBag,suturePack,spirometer,ringers1000,sodiumChloride,colloid,canulla14,canulla16,canulla18,canulla20,canulla22,canulla24,admin60,admin20,admin15,admin10,webcol,tegaderm,syringe50,syringe20,syringe10,syringe5,syringe3,needle18,needle21,handle,blade4,blade3,blade2,blade1,blade0,et25,et3,et35,et4,et45,et5,et55,et6,et65,et7,et75,et8,et85,tongue,childHolder,tubeTape,spareBatteries,micropore,peepValve,spaceBlanket,gauze,triBandages,vommit,multiPack);
        commentList = Arrays.asList(airComment,antennaeComment,batteryComment,bodyComment,brakeComment,brandingComment,dashboardComment,emergencyComment,exhaustComment,oilComment,fuelComment,headlightsComment,leftIndicatorComment,rightIndicatorComment,backComment,frontComment,jackComment,ledsComment,licenceComment,platesComment,radioComment,rearComment,reverseComment,sideComment,sirenComment,spareComment,treadComment,pressureComment,windowsComment,windscreenComment,ecgComment,adultEcgComment,childEcgComment,spareBatteryComment,spareEcgComment,oximeterComment,suctionUnitComment,suctionReservoirComment,chargingCableComment,suctionTubingComment,softSuctionComment,adultSuctionComment,paedSuctionComment,ventilatorKitComment,circuitHoseComment,circuitHoseFilterComment,co2AttachmentComment,oxygenCylinderComment,oxygenGaugeComment,oxygenCylinderKeyComment,syringePumpComment,powerCableComment,fiftySyringeComment,microboreComment,stretcherComment,strapsComment,mattressComment,sheetComment,pillowComment,pillowCaseComment,blanketComment,otherComment,adultKedComment,childKedComment,adultSplintComment,scoopStretcherComment,spinalBoardComment,headBlocksComment,basePlateComment,spiderHarnessComment,headStrapsComment,zipStretcherComment,longSplintsComment,shortSplintsComment,adultCollarComment,childCollarComment,fireExtinguisherComment,rescueHelmetComment,roadConesComment,reflectorVestsComment,wasteBinComment,medicalWasteComment,sharpsBinComment,bodyBagComment,glovesComment,portableOxygenComment,pinRegulatorComment,oxygenMainlineComment,oxygenFlowMeterComment,bullNoseComment,aedComment,adultDefibPadsComment,childDefibPadsComment,defibGelComment,asprinComment,gtnComment,clopidogrelComment,adenosineComment,amiodaroneComment,lignocaineComment,adrenalineComment,atropineComment,magnesiumComment,calciumComment,sodaComment,thiamineComment,diazepamComment,midazolamComment,promethazineComment,activatedCharcoalComment,flumazenilComment,naloxoneComment,morphineComment,dextroseComment,glucoseComment,fenoterolComment,bromideComment,corticosteriodsComment,furosemideComment,metaclopramideComment,buscopanComment,bigPlastersComment,smallPlastersComment,cetrimideComment,elastoplastRollComment,prfComment,doaComment,gopComment,torniquetComment,glucometerComment,gloves2Comment,bpComment,stethoscopeComment,rescueScissorsComment,thermometerComment,pupilTorchComment,testStripsComment,lancetsComment,satsComment,adultMaskComment,adultRebreatherComment,adultNebulizerComment,adultNasalComment,childMaskComment,childRebreatherComment,childNebuliserComment,childNasalComment,traumaPadsComment,traumaDressing100Comment,traumaDressing200Comment,bandage75Comment,bandage50Comment,stretchBandageComment,elastoplastComment,adultBvmComment,childBvmComment,infantBvmComment,adultMagillesComment,childMagillesComment,tube000Comment,tube00Comment,tube0Comment,tube1Comment,tube2Comment,tube3Comment,tube4Comment,tube5Comment,lma5Comment,lma4Comment,lma3Comment,lma2Comment,lma1Comment,uvcPackComment,maternityComment,needleCricComment,ngTubeComment,airwayPackComment,urinaryCatheterComment,urineBagComment,suturePackComment,spirometerComment,ringers1000Comment,sodiumChlorideComment,colloidComment,canulla14Comment,canulla16Comment,canulla18Comment,canulla20Comment,canulla22Comment,canulla24Comment,admin60Comment,admin20Comment,admin15Comment,admin10Comment,webcolComment,tegadermComment,syringe50Comment,syringe20Comment,syringe10Comment,syringe5Comment,syringe3Comment,needle18Comment,needle21Comment,handleComment,blade4Comment,blade3Comment,blade2Comment,blade1Comment,blade0Comment,et25Comment,et3Comment,et35Comment,et4Comment,et45Comment,et5Comment,et55Comment,et6Comment,et65Comment,et7Comment,et75Comment,et8Comment,et85Comment,tongueComment,childHolderComment,tubeTapeComment,spareBatteriesComment,microporeComment,peepValveComment,spaceBlanketComment,gauzeComment,triBandagesComment,vommitComment,multiPackComment);
        saved = cache.getStringProperty("vehicleSaveALS_ICU_AMB"+code);

        if( saved != null){
            try {
                load = new JSONObject(saved);
                VehicleNumber.setSelection(load.getInt("Vehicle Number"));
                controller.setText(load.getString("Controller"));
                driver.setText(load.getString("Driver"));
                inspectionTime.setText(load.getString("Inspection_Time"));
                checkedBy.setText(load.getString("Checked_By"));
                Iterator<String> keys = load.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    String item = load.getString(key);
                    for (int j = 0; j<checkBoxList.size();j++) {
                        if (checkBoxList.get(j) != null) {
                            if (key.equals(checkBoxList.get(j).getText().toString())) {
                                checkBoxList.get(j).setChecked(true);
                                commentList.get(j).setText(item);

                            }
                        }
                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        scheduledExecutorService = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ALS_ICU_AMB.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            save.put("Controller", controller.getText().toString());
                            save.put("Driver", driver.getText().toString());
                            save.put("Vehicle_Number", VehicleNumber.getSelectedItemPosition());
                            save.put("Checked_By", checkedBy.getText().toString());
                            save.put("Inspection_Time", inspectionTime.getText().toString());

                            for (int i = 0; i<checkBoxList.size();i++) {
                                if (checkBoxList.get(i) != null) {
                                    if (checkBoxList.get(i).isChecked()) {
                                        if (commentList.get(i).getText() != null) {
                                            save.put(checkBoxList.get(i).getText().toString(), commentList.get(i).getText().toString());
                                        }

                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        cache.setStringProperty("vehicleSaveALS_ICU_AMB"+code,save.toString());
                    }
                });
            }
        },30,30, TimeUnit.SECONDS);

        Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
    }

    public void Send(View v){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else connected = false;
        if (connected) {
            AsyncT send = new AsyncT();
            send.execute();
            Intent i = new Intent(getApplicationContext(), Home_Screen_Crew.class);
            cache.removeStringProperty("vehicleSaveALS_ICU_AMB"+code);
            scheduledExecutorService.shutdown();
            i.putExtra("code",code);
            i.putExtra("first","not");
            startActivity(i);
        }
        else {
            Toast.makeText(getApplicationContext(), "Please establish an internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            try {
                als_icu_amb.put("Ambulance_Type", "ALS/ICU Ambulance");
                als_icu_amb.put("Vehicle_Type", "Ambulance");
                als_icu_amb.put("Vehicle_Number", VehicleNumber.getSelectedItem().toString());
                als_icu_amb.put("Driver", driver.toString());
                als_icu_amb.put("Controller", controller.toString());
                als_icu_amb.put("Checked_By", checkedBy.toString());
                als_icu_amb.put("Inspection_Date", Date);
                als_icu_amb.put("Inspection_Time", inspectionTime.toString());

                for (int i = 0; i<checkBoxList.size();i++) {
                    if (checkBoxList.get(i) != null) {
                        if (!checkBoxList.get(i).isChecked()) {
                            if (commentList.get(i).getText() != null) {
                                als_icu_amb.put(checkBoxList.get(i).getText().toString(), commentList.get(i).getText().toString());
                            } else
                                als_icu_amb.put(checkBoxList.get(i).getText().toString(), "No Comment");

                        }
                    }
                }

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("req", als_icu_amb.toString()));

                Log.e("mainToPost", "mainToPost" + nameValuePairs.toString());

                // Use UrlEncodedFormEntity to send in proper format which we need
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                InputStream inputStream = response.getEntity().getContent();
                Login_Page.InputStreamToStringExample str = new Login_Page.InputStreamToStringExample();
                responseServer = str.getStringFromInputStream(inputStream);
                Log.e("response", "cake -----" + responseServer);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                response = new JSONObject(responseServer);
            } catch (JSONException e) {
                e.printStackTrace();
            }
           /* if (response.length() < 3) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
           else {
                Toast.makeText(getApplicationContext(), responseServer, Toast.LENGTH_SHORT).show();
            }*/
            Toast.makeText(getApplicationContext(), "sent", Toast.LENGTH_SHORT).show();
        }
    }

    public static class InputStreamToStringExample {

        public static void main(String[] args) throws IOException {

            // intilize an InputStream
            InputStream is = new ByteArrayInputStream("file content..blah blah".getBytes());

            String result = getStringFromInputStream(is);

            System.out.println(result);
            System.out.println("Done");

        }

        // convert InputStream to String
        public static String getStringFromInputStream(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }

    }

}
