package com.example.cape_medics;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cape_medics.TableLayout.Row;

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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ALS_ICU_AMB extends AppCompatActivity {
    Spinner VehicleNumber;
    TextView dateView,inspectionTime;
    JSONObject als_icu_amb, response, load, save;
    List<CheckBox> checkBoxList;
    List<EditText> commentList;
    String Date, responseServer,url, code, saved;
    Cache cache;
    boolean connected;
    ScheduledExecutorService scheduledExecutorService;

    private  EditText driver, controller, checkedBy;

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

    private static final int WrapContent = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static final int MatchParent = ViewGroup.LayoutParams.MATCH_PARENT;
    private RelativeLayout root,root2,root3,root4,root5,root6,root7,root8,root9;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-07-03 19:13:52 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        /*air = findViewById( R.id.air );
        antennae = findViewById( R.id.antennae );
        battery = findViewById( R.id.battery );
        body = findViewById( R.id.body );
        brake = findViewById( R.id.brake );
        branding = findViewById( R.id.branding );
        dashboard = findViewById( R.id.dashboard );
        emergency = findViewById( R.id.emergency );
        exhaust = findViewById( R.id.exhaust );
        oil = findViewById( R.id.oil );
        fuel = findViewById( R.id.fuel );
        headlights = findViewById( R.id.headlights );
        leftIndicator = findViewById( R.id.leftIndicator );
        rightIndicator = findViewById( R.id.rightIndicator );
        back = findViewById( R.id.back );
        front = findViewById( R.id.front );
        jack = findViewById( R.id.jack );
        leds = findViewById( R.id.leds );
        licence = findViewById( R.id.licence );
        plates = findViewById( R.id.plates );
        radio = findViewById( R.id.radio );
        rear = findViewById( R.id.rear );
        reverse = findViewById( R.id.reverse );
        side = findViewById( R.id.side );
        siren = findViewById( R.id.siren );
        spare = findViewById( R.id.spare );
        tread = findViewById( R.id.tread );
        pressure = findViewById( R.id.pressure );
        windows = findViewById( R.id.windows );
        windscreen = findViewById( R.id.windscreen );
        airComment = findViewById( R.id.airComment );
        antennaeComment = findViewById( R.id.antennaeComment );
        batteryComment = findViewById( R.id.batteryComment );
        bodyComment = findViewById( R.id.bodyComment );
        brakeComment = findViewById( R.id.brakeComment );
        brandingComment = findViewById( R.id.brandingComment );
        dashboardComment = findViewById( R.id.dashboardComment );
        emergencyComment = findViewById( R.id.emergencyComment );
        exhaustComment = findViewById( R.id.exhaustComment );
        oilComment = findViewById( R.id.oilComment );
        fuelComment = findViewById( R.id.fuelComment );
        headlightsComment = findViewById( R.id.headlightsComment );
        leftIndicatorComment = findViewById( R.id.leftIndicatorComment );
        rightIndicatorComment = findViewById( R.id.rightIndicatorComment );
        backComment = findViewById( R.id.backComment );
        frontComment = findViewById( R.id.frontComment );
        jackComment = findViewById( R.id.jackComment );
        ledsComment = findViewById( R.id.ledsComment );
        licenceComment = findViewById( R.id.licenceComment );
        platesComment = findViewById( R.id.platesComment );
        radioComment = findViewById( R.id.radioComment );
        rearComment = findViewById( R.id.rearComment );
        reverseComment = findViewById( R.id.reverseComment );
        sideComment = findViewById( R.id.sideComment );
        sirenComment = findViewById( R.id.sirenComment );
        spareComment = findViewById( R.id.spareComment );
        treadComment = findViewById( R.id.treadComment );
        pressureComment = findViewById( R.id.pressureComment );
        windowsComment = findViewById( R.id.windowsComment );
        windscreenComment = findViewById( R.id.windscreenComment );
        ecg = findViewById( R.id.ecg );
        ecgQty = findViewById( R.id.ecgQty );
        ecgComment = findViewById( R.id.ecgComment );
        adultEcg = findViewById( R.id.adultEcg );
        adultEcgQty = findViewById( R.id.adultEcgQty );
        adultEcgComment = findViewById( R.id.adultEcgComment );
        childEcg = findViewById( R.id.childEcg );
        childEcgQty = findViewById( R.id.childEcgQty );
        childEcgComment = findViewById( R.id.childEcgComment );
        spareBattery = findViewById( R.id.spareBattery );
        spareBatteryQty = findViewById( R.id.spareBatteryQty );
        spareBatteryComment = findViewById( R.id.spareBatteryComment );
        spareEcg = findViewById( R.id.spareEcg );
        spareEcgQty = findViewById( R.id.spareEcgQty );
        spareEcgComment = findViewById( R.id.spareEcgComment );
        oximeter = findViewById( R.id.oximeter );
        oximeterQty = findViewById( R.id.oximeterQty );
        oximeterComment = findViewById( R.id.oximeterComment );
        suctionUnit = findViewById( R.id.suctionUnit );
        suctionUnitQty = findViewById( R.id.suctionUnitQty );
        suctionUnitComment = findViewById( R.id.suctionUnitComment );
        suctionReservoir = findViewById( R.id.suctionReservoir );
        suctionReservoirQty = findViewById( R.id.suctionReservoirQty );
        suctionReservoirComment = findViewById( R.id.suctionReservoirComment );
        chargingCable = findViewById( R.id.chargingCable );
        chargingCableQty = findViewById( R.id.chargingCableQty );
        chargingCableComment = findViewById( R.id.chargingCableComment );
        suctionTubing = findViewById( R.id.suctionTubing );
        suctionTubingQty = findViewById( R.id.suctionTubingQty );
        suctionTubingComment = findViewById( R.id.suctionTubingComment );
        softSuction = findViewById( R.id.softSuction );
        softSuctionQty = findViewById( R.id.softSuctionQty );
        softSuctionComment = findViewById( R.id.softSuctionComment );
        adultSuction = findViewById( R.id.adultSuction );
        adultSuctionQty = findViewById( R.id.adultSuctionQty );
        adultSuctionComment = findViewById( R.id.adultSuctionComment );
        paedSuction = findViewById( R.id.paedSuction );
        paedSuctionQty = findViewById( R.id.paedSuctionQty );
        paedSuctionComment = findViewById( R.id.paedSuctionComment );
        ventilatorKit = findViewById( R.id.ventilatorKit );
        ventilatorKitQty = findViewById( R.id.ventilatorKitQty );
        ventilatorKitComment = findViewById( R.id.ventilatorKitComment );
        circuitHose = findViewById( R.id.circuitHose );
        circuitHoseQty = findViewById( R.id.circuitHoseQty );
        circuitHoseComment = findViewById( R.id.circuitHoseComment );
        circuitHoseFilter = findViewById( R.id.circuitHoseFilter );
        circuitHoseFilterQty = findViewById( R.id.circuitHoseFilterQty );
        circuitHoseFilterComment = findViewById( R.id.circuitHoseFilterComment );
        co2Attachment = findViewById( R.id.co2Attachment );
        co2AttachmentQty = findViewById( R.id.co2AttachmentQty );
        co2AttachmentComment = findViewById( R.id.co2AttachmentComment );
        oxygenCylinder = findViewById( R.id.oxygenCylinder );
        oxygenCylinderQty = findViewById( R.id.oxygenCylinderQty );
        oxygenCylinderComment = findViewById( R.id.oxygenCylinderComment );
        oxygenGauge = findViewById( R.id.oxygenGauge );
        oxygenGaugeQty = findViewById( R.id.oxygenGaugeQty );
        oxygenGaugeComment = findViewById( R.id.oxygenGaugeComment );
        oxygenCylinderKey = findViewById( R.id.oxygenCylinderKey );
        oxygenCylinderKeyQty = findViewById( R.id.oxygenCylinderKeyQty );
        oxygenCylinderKeyComment = findViewById( R.id.oxygenCylinderKeyComment );
        syringePump = findViewById( R.id.syringePump );
        syringePumpQty = findViewById( R.id.syringePumpQty );
        syringePumpComment = findViewById( R.id.syringePumpComment );
        powerCable = findViewById( R.id.powerCable );
        powerCableQty = findViewById( R.id.powerCableQty );
        powerCableComment = findViewById( R.id.powerCableComment );
        fiftySyringe = findViewById( R.id.fiftySyringe );
        fiftySyringeQty = findViewById( R.id.fiftySyringeQty );
        fiftySyringeComment = findViewById( R.id.fiftySyringeComment );
        microbore = findViewById( R.id.microbore );
        microboreQty = findViewById( R.id.microboreQty );
        microboreComment = findViewById( R.id.microboreComment );
        stretcher = findViewById( R.id.stretcher );
        stretcherQty = findViewById( R.id.stretcherQty );
        stretcherComment = findViewById( R.id.stretcherComment );
        straps = findViewById( R.id.straps );
        strapsQty = findViewById( R.id.strapsQty );
        strapsComment = findViewById( R.id.strapsComment );
        mattress = findViewById( R.id.mattress );
        mattressQty = findViewById( R.id.mattressQty );
        mattressComment = findViewById( R.id.mattressComment );
        sheet = findViewById( R.id.sheet );
        sheetQty = findViewById( R.id.sheetQty );
        sheetComment = findViewById( R.id.sheetComment );
        pillow = findViewById( R.id.pillow );
        pillowQty = findViewById( R.id.pillowQty );
        pillowComment = findViewById( R.id.pillowComment );
        pillowCase = findViewById( R.id.pillowCase );
        pillowCaseQty = findViewById( R.id.pillowCaseQty );
        pillowCaseComment = findViewById( R.id.pillowCaseComment );
        blanket = findViewById( R.id.blanket );
        blanketQty = findViewById( R.id.blanketQty );
        blanketComment = findViewById( R.id.blanketComment );
        other = findViewById( R.id.other );
        otherQty = findViewById( R.id.otherQty );
        otherComment = findViewById( R.id.otherComment );
        adultKed = findViewById( R.id.adultKed );
        adultKedQty = findViewById( R.id.adultKedQty );
        adultKedComment = findViewById( R.id.adultKedComment );
        childKed = findViewById( R.id.childKed );
        childKedQty = findViewById( R.id.childKedQty );
        childKedComment = findViewById( R.id.childKedComment );
        adultSplint = findViewById( R.id.adultSplint );
        adultSplintQty = findViewById( R.id.adultSplintQty );
        adultSplintComment = findViewById( R.id.adultSplintComment );
        scoopStretcher = findViewById( R.id.scoopStretcher );
        scoopStretcherQty = findViewById( R.id.scoopStretcherQty );
        scoopStretcherComment = findViewById( R.id.scoopStretcherComment );
        spinalBoard = findViewById( R.id.spinalBoard );
        spinalBoardQty = findViewById( R.id.spinalBoardQty );
        spinalBoardComment = findViewById( R.id.spinalBoardComment );
        headBlocks = findViewById( R.id.headBlocks );
        headBlocksQty = findViewById( R.id.headBlocksQty );
        headBlocksComment = findViewById( R.id.headBlocksComment );
        basePlate = findViewById( R.id.basePlate );
        basePlateQty = findViewById( R.id.basePlateQty );
        basePlateComment = findViewById( R.id.basePlateComment );
        spiderHarness = findViewById( R.id.spiderHarness );
        spiderHarnessQty = findViewById( R.id.spiderHarnessQty );
        spiderHarnessComment = findViewById( R.id.spiderHarnessComment );
        headStraps = findViewById( R.id.headStraps );
        headStrapsQty = findViewById( R.id.headStrapsQty );
        headStrapsComment = findViewById( R.id.headStrapsComment );
        zipStretcher = findViewById( R.id.zipStretcher );
        zipStretcherBoardQty = findViewById( R.id.zipStretcherBoardQty );
        zipStretcherComment = findViewById( R.id.zipStretcherComment );
        longSplints = findViewById( R.id.longSplints );
        longSplintsQty = findViewById( R.id.longSplintsQty );
        longSplintsComment = findViewById( R.id.longSplintsComment );
        shortSplints = findViewById( R.id.shortSplints );
        shortSplintsQty = findViewById( R.id.shortSplintsQty );
        shortSplintsComment = findViewById( R.id.shortSplintsComment );
        adultCollar = findViewById( R.id.adultCollar );
        adultCollarQty = findViewById( R.id.adultCollarQty );
        adultCollarComment = findViewById( R.id.adultCollarComment );
        childCollar = findViewById( R.id.childCollar );
        childCollarQty = findViewById( R.id.childCollarQty );
        childCollarComment = findViewById( R.id.childCollarComment );
        fireExtinguisher = findViewById( R.id.fireExtinguisher );
        fireExtinguisherQty = findViewById( R.id.fireExtinguisherQty );
        fireExtinguisherComment = findViewById( R.id.fireExtinguisherComment );
        rescueHelmet = findViewById( R.id.rescueHelmet );
        rescueHelmetQty = findViewById( R.id.rescueHelmetQty );
        rescueHelmetComment = findViewById( R.id.rescueHelmetComment );
        roadCones = findViewById( R.id.roadCones );
        roadConesQty = findViewById( R.id.roadConesQty );
        roadConesComment = findViewById( R.id.roadConesComment );
        reflectorVests = findViewById( R.id.reflectorVests );
        reflectorVestsQty = findViewById( R.id.reflectorVestsQty );
        reflectorVestsComment = findViewById( R.id.reflectorVestsComment );
        wasteBin = findViewById( R.id.wasteBin );
        wasteBinQty = findViewById( R.id.wasteBinQty );
        wasteBinComment = findViewById( R.id.wasteBinComment );
        medicalWaste = findViewById( R.id.medicalWaste );
        medicalWasteQty = findViewById( R.id.medicalWasteQty );
        medicalWasteComment = findViewById( R.id.medicalWasteComment );
        sharpsBin = findViewById( R.id.sharpsBin );
        sharpsBinQty = findViewById( R.id.sharpsBinQty );
        sharpsBinComment = findViewById( R.id.sharpsBinComment );
        bodyBag = findViewById( R.id.bodyBag );
        bodyBagQty = findViewById( R.id.bodyBagQty );
        bodyBagComment = findViewById( R.id.bodyBagComment );
        gloves = findViewById( R.id.gloves );
        glovesQty = findViewById( R.id.glovesQty );
        glovesComment = findViewById( R.id.glovesComment );
        portableOxygen = findViewById( R.id.portableOxygen );
        portableOxygenQty = findViewById( R.id.portableOxygenQty );
        portableOxygenComment = findViewById( R.id.portableOxygenComment );
        pinRegulator = findViewById( R.id.pinRegulator );
        pinRegulatorQty = findViewById( R.id.pinRegulatorQty );
        pinRegulatorComment = findViewById( R.id.pinRegulatorComment );
        oxygenMainline = findViewById( R.id.oxygenMainline );
        oxygenMainlineQty = findViewById( R.id.oxygenMainlineQty );
        oxygenMainlineComment = findViewById( R.id.oxygenMainlineComment );
        oxygenFlowMeter = findViewById( R.id.oxygenFlowMeter );
        oxygenFlowMeterQty = findViewById( R.id.oxygenFlowMeterQty );
        oxygenFlowMeterComment = findViewById( R.id.oxygenFlowMeterComment );
        bullNose = findViewById( R.id.bullNose );
        bullNoseQty = findViewById( R.id.bullNoseQty );
        bullNoseComment = findViewById( R.id.bullNoseComment );
        aed = findViewById( R.id.aed );
        aedQty = findViewById( R.id.aedQty );
        aedComment = findViewById( R.id.aedComment );
        adultDefibPads = findViewById( R.id.adultDefibPads );
        adultDefibPadsQty = findViewById( R.id.adultDefibPadsQty );
        adultDefibPadsComment = findViewById( R.id.adultDefibPadsComment );
        childDefibPads = findViewById( R.id.childDefibPads );
        childDefibPadsQty = findViewById( R.id.childDefibPadsQty );
        childDefibPadsComment = findViewById( R.id.childDefibPadsComment );
        defibGel = findViewById( R.id.defibGel );
        defibGelQty = findViewById( R.id.defibGelQty );
        defibGelComment = findViewById( R.id.defibGelComment );
        asprin = findViewById( R.id.asprin );
        asprinQty = findViewById( R.id.asprinQty );
        asprinComment = findViewById( R.id.asprinComment );
        gtn = findViewById( R.id.gtn );
        gtnQty = findViewById( R.id.gtnQty );
        gtnComment = findViewById( R.id.gtnComment );
        clopidogrel = findViewById( R.id.clopidogrel );
        clopidogrelQty = findViewById( R.id.clopidogrelQty );
        clopidogrelComment = findViewById( R.id.clopidogrelComment );
        adenosine = findViewById( R.id.adenosine );
        adenosineQty = findViewById( R.id.adenosineQty );
        adenosineComment = findViewById( R.id.adenosineComment );
        amiodarone = findViewById( R.id.amiodarone );
        amiodaroneQty = findViewById( R.id.amiodaroneQty );
        amiodaroneComment = findViewById( R.id.amiodaroneComment );
        lignocaine = findViewById( R.id.lignocaine );
        lignocaineQty = findViewById( R.id.lignocaineQty );
        lignocaineComment = findViewById( R.id.lignocaineComment );
        adrenaline = findViewById( R.id.adrenaline );
        adrenalineQty = findViewById( R.id.adrenalineQty );
        adrenalineComment = findViewById( R.id.adrenalineComment );
        atropine = findViewById( R.id.atropine );
        atropineQty = findViewById( R.id.atropineQty );
        atropineComment = findViewById( R.id.atropineComment );
        magnesium = findViewById( R.id.magnesium );
        magnesiumQty = findViewById( R.id.magnesiumQty );
        magnesiumComment = findViewById( R.id.magnesiumComment );
        calcium = findViewById( R.id.calcium );
        calciumQty = findViewById( R.id.calciumQty );
        calciumComment = findViewById( R.id.calciumComment );
        soda = findViewById( R.id.soda );
        sodaQty = findViewById( R.id.sodaQty );
        sodaComment = findViewById( R.id.sodaComment );
        thiamine = findViewById( R.id.thiamine );
        thiamineQty = findViewById( R.id.thiamineQty );
        thiamineComment = findViewById( R.id.thiamineComment );
        diazepam = findViewById( R.id.diazepam );
        diazepamQty = findViewById( R.id.diazepamQty );
        diazepamComment = findViewById( R.id.diazepamComment );
        midazolam = findViewById( R.id.midazolam );
        midazolamQty = findViewById( R.id.midazolamQty );
        midazolamComment = findViewById( R.id.midazolamComment );
        promethazine = findViewById( R.id.promethazine );
        promethazineQty = findViewById( R.id.promethazineQty );
        promethazineComment = findViewById( R.id.promethazineComment );
        activatedCharcoal = findViewById( R.id.activatedCharcoal );
        activatedCharcoalQty = findViewById( R.id.activatedCharcoalQty );
        activatedCharcoalComment = findViewById( R.id.activatedCharcoalComment );
        flumazenil = findViewById( R.id.flumazenil );
        flumazenilQty = findViewById( R.id.flumazenilQty );
        flumazenilComment = findViewById( R.id.flumazenilComment );
        naloxone = findViewById( R.id.naloxone );
        naloxoneQty = findViewById( R.id.naloxoneQty );
        naloxoneComment = findViewById( R.id.naloxoneComment );
        morphine = findViewById( R.id.morphine );
        morphineQty = findViewById( R.id.morphineQty );
        morphineComment = findViewById( R.id.morphineComment );
        dextrose = findViewById( R.id.dextrose );
        dextroseQty = findViewById( R.id.dextroseQty );
        dextroseComment = findViewById( R.id.dextroseComment );
        glucose = findViewById( R.id.glucose );
        glucoseQty = findViewById( R.id.glucoseQty );
        glucoseComment = findViewById( R.id.glucoseComment );
        fenoterol = findViewById( R.id.fenoterol );
        fenoterolQty = findViewById( R.id.fenoterolQty );
        fenoterolComment = findViewById( R.id.fenoterolComment );
        bromide = findViewById( R.id.bromide );
        bromideQty = findViewById( R.id.bromideQty );
        bromideComment = findViewById( R.id.bromideComment );
        corticosteriods = findViewById( R.id.corticosteriods );
        corticosteriodsQty = findViewById( R.id.corticosteriodsQty );
        corticosteriodsComment = findViewById( R.id.corticosteriodsComment );
        furosemide = findViewById( R.id.furosemide );
        furosemideQty = findViewById( R.id.furosemideQty );
        furosemideComment = findViewById( R.id.furosemideComment );
        metaclopramide = findViewById( R.id.metaclopramide );
        metaclopramideQty = findViewById( R.id.metaclopramideQty );
        metaclopramideComment = findViewById( R.id.metaclopramideComment );
        buscopan = findViewById( R.id.buscopan );
        buscopanQty = findViewById( R.id.buscopanQty );
        buscopanComment = findViewById( R.id.buscopanComment );
        bigPlasters = findViewById( R.id.bigPlasters );
        bigPlastersQty = findViewById( R.id.bigPlastersQty );
        bigPlastersComment = findViewById( R.id.bigPlastersComment );
        smallPlasters = findViewById( R.id.smallPlasters );
        smallPlastersQty = findViewById( R.id.smallPlastersQty );
        smallPlastersComment = findViewById( R.id.smallPlastersComment );
        cetrimide = findViewById( R.id.cetrimide );
        cetrimideQty = findViewById( R.id.cetrimideQty );
        cetrimideComment = findViewById( R.id.cetrimideComment );
        elastoplastRoll = findViewById( R.id.elastoplastRoll );
        elastoplastRollQty = findViewById( R.id.elastoplastRollQty );
        elastoplastRollComment = findViewById( R.id.elastoplastRollComment );
        prf = findViewById( R.id.prf );
        prfQty = findViewById( R.id.prfQty );
        prfComment = findViewById( R.id.prfComment );
        doa = findViewById( R.id.doa );
        doaQty = findViewById( R.id.doaQty );
        doaComment = findViewById( R.id.doaComment );
        gop = findViewById( R.id.gop );
        gopQty = findViewById( R.id.gopQty );
        gopComment = findViewById( R.id.gopComment );
        torniquet = findViewById( R.id.torniquet );
        torniquetQty = findViewById( R.id.torniquetQty );
        torniquetComment = findViewById( R.id.torniquetComment );
        glucometer = findViewById( R.id.glucometer );
        glucometerQty = findViewById( R.id.glucometerQty );
        glucometerComment = findViewById( R.id.glucometerComment );
        gloves2 = findViewById( R.id.gloves2 );
        gloves2Qty = findViewById( R.id.gloves2Qty );
        gloves2Comment = findViewById( R.id.gloves2Comment );
        bp = findViewById( R.id.bp );
        bpQty = findViewById( R.id.bpQty );
        bpComment = findViewById( R.id.bpComment );
        stethoscope = findViewById( R.id.stethoscope );
        stethoscopeQty = findViewById( R.id.stethoscopeQty );
        stethoscopeComment = findViewById( R.id.stethoscopeComment );
        rescueScissors = findViewById( R.id.rescueScissors );
        rescueScissorsQty = findViewById( R.id.rescueScissorsQty );
        rescueScissorsComment = findViewById( R.id.rescueScissorsComment );
        thermometer = findViewById( R.id.thermometer );
        thermometerQty = findViewById( R.id.thermometerQty );
        thermometerComment = findViewById( R.id.thermometerComment );
        pupilTorch = findViewById( R.id.pupilTorch );
        pupilTorchQty = findViewById( R.id.pupilTorchQty );
        pupilTorchComment = findViewById( R.id.pupilTorchComment );
        testStrips = findViewById( R.id.testStrips );
        testStripsQty = findViewById( R.id.testStripsQty );
        testStripsComment = findViewById( R.id.testStripsComment );
        lancets = findViewById( R.id.lancets );
        lancetsQty = findViewById( R.id.lancetsQty );
        lancetsComment = findViewById( R.id.lancetsComment );
        sats = findViewById( R.id.sats );
        satsQty = findViewById( R.id.satsQty );
        satsComment = findViewById( R.id.satsComment );
        adultMask = findViewById( R.id.adultMask );
        adultMaskQty = findViewById( R.id.adultMaskQty );
        adultMaskComment = findViewById( R.id.adultMaskComment );
        adultRebreather = findViewById( R.id.adultRebreather );
        adultRebreatherQty = findViewById( R.id.adultRebreatherQty );
        adultRebreatherComment = findViewById( R.id.adultRebreatherComment );
        adultNebulizer = findViewById( R.id.adultNebulizer );
        adultNebulizerQty = findViewById( R.id.adultNebulizerQty );
        adultNebulizerComment = findViewById( R.id.adultNebulizerComment );
        adultNasal = findViewById( R.id.adultNasal );
        adultNasalQty = findViewById( R.id.adultNasalQty );
        adultNasalComment = findViewById( R.id.adultNasalComment );
        childMask = findViewById( R.id.childMask );
        childMaskQty = findViewById( R.id.childMaskQty );
        childMaskComment = findViewById( R.id.childMaskComment );
        childRebreather = findViewById( R.id.childRebreather );
        childRebreatherQty = findViewById( R.id.childRebreatherQty );
        childRebreatherComment = findViewById( R.id.childRebreatherComment );
        childNebuliser = findViewById( R.id.childNebuliser );
        childNebuliserQty = findViewById( R.id.childNebuliserQty );
        childNebuliserComment = findViewById( R.id.childNebuliserComment );
        childNasal = findViewById( R.id.childNasal );
        childNasalQty = findViewById( R.id.childNasalQty );
        childNasalComment = findViewById( R.id.childNasalComment );
        traumaPads = findViewById( R.id.traumaPads );
        traumaPadsQty = findViewById( R.id.traumaPadsQty );
        traumaPadsComment = findViewById( R.id.traumaPadsComment );
        traumaDressing100 = findViewById( R.id.traumaDressing100 );
        traumaDressing100Qty = findViewById( R.id.traumaDressing100Qty );
        traumaDressing100Comment = findViewById( R.id.traumaDressing100Comment );
        traumaDressing200 = findViewById( R.id.traumaDressing200 );
        traumaDressing200Qty = findViewById( R.id.traumaDressing200Qty );
        traumaDressing200Comment = findViewById( R.id.traumaDressing200Comment );
        bandage75 = findViewById( R.id.bandage75 );
        bandage75Qty = findViewById( R.id.bandage75Qty );
        bandage75Comment = findViewById( R.id.bandage75Comment );
        bandage50 = findViewById( R.id.bandage50 );
        bandage50Qty = findViewById( R.id.bandage50Qty );
        bandage50Comment = findViewById( R.id.bandage50Comment );
        stretchBandage = findViewById( R.id.stretchBandage );
        stretchBandageQty = findViewById( R.id.stretchBandageQty );
        stretchBandageComment = findViewById( R.id.stretchBandageComment );
        elastoplast = findViewById( R.id.elastoplast );
        elastoplastQty = findViewById( R.id.elastoplastQty );
        elastoplastComment = findViewById( R.id.elastoplastComment );
        adultBvm = findViewById( R.id.adultBvm );
        adultBvmQty = findViewById( R.id.adultBvmQty );
        adultBvmComment = findViewById( R.id.adultBvmComment );
        childBvm = findViewById( R.id.childBvm );
        childBvmQty = findViewById( R.id.childBvmQty );
        childBvmComment = findViewById( R.id.childBvmComment );
        infantBvm = findViewById( R.id.infantBvm );
        infantBvmQty = findViewById( R.id.infantBvmQty );
        infantBvmComment = findViewById( R.id.infantBvmComment );
        adultMagilles = findViewById( R.id.adultMagilles );
        adultMagillesQty = findViewById( R.id.adultMagillesQty );
        adultMagillesComment = findViewById( R.id.adultMagillesComment );
        childMagilles = findViewById( R.id.childMagilles );
        childMagillesQty = findViewById( R.id.childMagillesQty );
        childMagillesComment = findViewById( R.id.childMagillesComment );
        tube000 = findViewById( R.id.tube000 );
        tube000Qty = findViewById( R.id.tube000Qty );
        tube000Comment = findViewById( R.id.tube000Comment );
        tube00 = findViewById( R.id.tube00 );
        tube00Qty = findViewById( R.id.tube00Qty );
        tube00Comment = findViewById( R.id.tube00Comment );
        tube0 = findViewById( R.id.tube0 );
        tube0Qty = findViewById( R.id.tube0Qty );
        tube0Comment = findViewById( R.id.tube0Comment );
        tube1 = findViewById( R.id.tube1 );
        tube1Qty = findViewById( R.id.tube1Qty );
        tube1Comment = findViewById( R.id.tube1Comment );
        tube2 = findViewById( R.id.tube2 );
        tube2Qty = findViewById( R.id.tube2Qty );
        tube2Comment = findViewById( R.id.tube2Comment );
        tube3 = findViewById( R.id.tube3 );
        tube3Qty = findViewById( R.id.tube3Qty );
        tube3Comment = findViewById( R.id.tube3Comment );
        tube4 = findViewById( R.id.tube4 );
        tube4Qty = findViewById( R.id.tube4Qty );
        tube4Comment = findViewById( R.id.tube4Comment );
        tube5 = findViewById( R.id.tube5 );
        tube5Qty = findViewById( R.id.tube5Qty );
        tube5Comment = findViewById( R.id.tube5Comment );
        lma5 = findViewById( R.id.lma5 );
        lma5Qty = findViewById( R.id.lma5Qty );
        lma5Comment = findViewById( R.id.lma5Comment );
        lma4 = findViewById( R.id.lma4 );
        lma4Qty = findViewById( R.id.lma4Qty );
        lma4Comment = findViewById( R.id.lma4Comment );
        lma3 = findViewById( R.id.lma3 );
        lma3Qty = findViewById( R.id.lma3Qty );
        lma3Comment = findViewById( R.id.lma3Comment );
        lma2 = findViewById( R.id.lma2 );
        lma2Qty = findViewById( R.id.lma2Qty );
        lma2Comment = findViewById( R.id.lma2Comment );
        lma1 = findViewById( R.id.lma1 );
        lma1Qty = findViewById( R.id.lma1Qty );
        lma1Comment = findViewById( R.id.lma1Comment );
        uvcPack = findViewById( R.id.uvcPack );
        uvcPackQty = findViewById( R.id.uvcPackQty );
        uvcPackComment = findViewById( R.id.uvcPackComment );
        maternity = findViewById( R.id.maternity );
        maternityQty = findViewById( R.id.maternityQty );
        maternityComment = findViewById( R.id.maternityComment );
        needleCric = findViewById( R.id.needleCric );
        needleCricQty = findViewById( R.id.needleCricQty );
        needleCricComment = findViewById( R.id.needleCricComment );
        ngTube = findViewById( R.id.ngTube );
        ngTubeQty = findViewById( R.id.ngTubeQty );
        ngTubeComment = findViewById( R.id.ngTubeComment );
        airwayPack = findViewById( R.id.airwayPack );
        airwayPackQty = findViewById( R.id.airwayPackQty );
        airwayPackComment = findViewById( R.id.airwayPackComment );
        urinaryCatheter = findViewById( R.id.urinaryCatheter );
        urinaryCatheterQty = findViewById( R.id.urinaryCatheterQty );
        urinaryCatheterComment = findViewById( R.id.urinaryCatheterComment );
        urineBag = findViewById( R.id.urineBag );
        urineBagQty = findViewById( R.id.urineBagQty );
        urineBagComment = findViewById( R.id.urineBagComment );
        suturePack = findViewById( R.id.suturePack );
        suturePackQty = findViewById( R.id.suturePackQty );
        suturePackComment = findViewById( R.id.suturePackComment );
        spirometer = findViewById( R.id.spirometer );
        spirometerQty = findViewById( R.id.spirometerQty );
        spirometerComment = findViewById( R.id.spirometerComment );
        ringers1000 = findViewById( R.id.ringers1000 );
        ringers1000Qty = findViewById( R.id.ringers1000Qty );
        ringers1000Comment = findViewById( R.id.ringers1000Comment );
        sodiumChloride = findViewById( R.id.sodiumChloride );
        sodiumChlorideQty = findViewById( R.id.sodiumChlorideQty );
        sodiumChlorideComment = findViewById( R.id.sodiumChlorideComment );
        colloid = findViewById( R.id.colloid );
        colloidQty = findViewById( R.id.colloidQty );
        colloidComment = findViewById( R.id.colloidComment );
        canulla14 = findViewById( R.id.canulla14 );
        canulla14Qty = findViewById( R.id.canulla14Qty );
        canulla14Comment = findViewById( R.id.canulla14Comment );
        canulla16 = findViewById( R.id.canulla16 );
        canulla16Qty = findViewById( R.id.canulla16Qty );
        canulla16Comment = findViewById( R.id.canulla16Comment );
        canulla18 = findViewById( R.id.canulla18 );
        canulla18Qty = findViewById( R.id.canulla18Qty );
        canulla18Comment = findViewById( R.id.canulla18Comment );
        canulla20 = findViewById( R.id.canulla20 );
        canulla20Qty = findViewById( R.id.canulla20Qty );
        canulla20Comment = findViewById( R.id.canulla20Comment );
        canulla22 = findViewById( R.id.canulla22 );
        canulla22Qty = findViewById( R.id.canulla22Qty );
        canulla22Comment = findViewById( R.id.canulla22Comment );
        canulla24 = findViewById( R.id.canulla24 );
        canulla24Qty = findViewById( R.id.canulla24Qty );
        canulla24Comment = findViewById( R.id.canulla24Comment );
        admin60 = findViewById( R.id.admin60 );
        admin60Qty = findViewById( R.id.admin60Qty );
        admin60Comment = findViewById( R.id.admin60Comment );
        admin20 = findViewById( R.id.admin20 );
        admin20Qty = findViewById( R.id.admin20Qty );
        admin20Comment = findViewById( R.id.admin20Comment );
        admin15 = findViewById( R.id.admin15 );
        admin15Qty = findViewById( R.id.admin15Qty );
        admin15Comment = findViewById( R.id.admin15Comment );
        admin10 = findViewById( R.id.admin10 );
        admin10Qty = findViewById( R.id.admin10Qty );
        admin10Comment = findViewById( R.id.admin10Comment );
        webcol = findViewById( R.id.webcol );
        webcolQty = findViewById( R.id.webcolQty );
        webcolComment = findViewById( R.id.webcolComment );
        tegaderm = findViewById( R.id.tegaderm );
        tegadermQty = findViewById( R.id.tegadermQty );
        tegadermComment = findViewById( R.id.tegadermComment );
        syringe50 = findViewById( R.id.syringe50 );
        syringe50Qty = findViewById( R.id.syringe50Qty );
        syringe50Comment = findViewById( R.id.syringe50Comment );
        syringe20 = findViewById( R.id.syringe20 );
        syringe20Qty = findViewById( R.id.syringe20Qty );
        syringe20Comment = findViewById( R.id.syringe20Comment );
        syringe10 = findViewById( R.id.syringe10 );
        syringe10Qty = findViewById( R.id.syringe10Qty );
        syringe10Comment = findViewById( R.id.syringe10Comment );
        syringe5 = findViewById( R.id.syringe5 );
        syringe5Qty = findViewById( R.id.syringe5Qty );
        syringe5Comment = findViewById( R.id.syringe5Comment );
        syringe3 = findViewById( R.id.syringe3 );
        syringe3Qty = findViewById( R.id.syringe3Qty );
        syringe3Comment = findViewById( R.id.syringe3Comment );
        needle18 = findViewById( R.id.needle18 );
        needle18Qty = findViewById( R.id.needle18Qty );
        needle18Comment = findViewById( R.id.needle18Comment );
        needle21 = findViewById( R.id.needle21 );
        needle21Qty = findViewById( R.id.needle21Qty );
        needle21Comment = findViewById( R.id.needle21Comment );
        handle = findViewById( R.id.handle );
        handleQty = findViewById( R.id.handleQty );
        handleComment = findViewById( R.id.handleComment );
        blade4 = findViewById( R.id.blade4 );
        blade4Qty = findViewById( R.id.blade4Qty );
        blade4Comment = findViewById( R.id.blade4Comment );
        blade3 = findViewById( R.id.blade3 );
        blade3Qty = findViewById( R.id.blade3Qty );
        blade3Comment = findViewById( R.id.blade3Comment );
        blade2 = findViewById( R.id.blade2 );
        blade2Qty = findViewById( R.id.blade2Qty );
        blade2Comment = findViewById( R.id.blade2Comment );
        blade1 = findViewById( R.id.blade1 );
        blade1Qty = findViewById( R.id.blade1Qty );
        blade1Comment = findViewById( R.id.blade1Comment );
        blade0 = findViewById( R.id.blade0 );
        blade0Qty = findViewById( R.id.blade0Qty );
        blade0Comment = findViewById( R.id.blade0Comment );
        et25 = findViewById( R.id.et2_5 );
        et25Qty = findViewById( R.id.et2_5Qty );
        et25Comment = findViewById( R.id.et2_5Comment );
        et3 = findViewById( R.id.et3 );
        et3Qty = findViewById( R.id.et3Qty );
        et3Comment = findViewById( R.id.et3Comment );
        et35 = findViewById( R.id.et3_5 );
        et35Qty = findViewById( R.id.et3_5Qty );
        et35Comment = findViewById( R.id.et3_5Comment );
        et4 = findViewById( R.id.et4 );
        et4Qty = findViewById( R.id.et4Qty );
        et4Comment = findViewById( R.id.et4Comment );
        et45 = findViewById( R.id.et4_5 );
        et45Qty = findViewById( R.id.et4_5Qty );
        et45Comment = findViewById( R.id.et4_5Comment );
        et5 = findViewById( R.id.et5 );
        et5Qty = findViewById( R.id.et5Qty );
        et5Comment = findViewById( R.id.et5Comment );
        et55 = findViewById( R.id.et5_5 );
        et55Qty = findViewById( R.id.et5_5Qty );
        et55Comment = findViewById( R.id.et5_5Comment );
        et6 = findViewById( R.id.et6 );
        et6Qty = findViewById( R.id.et6Qty );
        et6Comment = findViewById( R.id.et6Comment );
        et65 = findViewById( R.id.et6_5 );
        et65Qty = findViewById( R.id.et6_5Qty );
        et65Comment = findViewById( R.id.et6_5Comment );
        et7 = findViewById( R.id.et7 );
        et7Qty = findViewById( R.id.et7Qty );
        et7Comment = findViewById( R.id.et7Comment );
        et75 = findViewById( R.id.et7_5 );
        et75Qty = findViewById( R.id.et7_5Qty );
        et75Comment = findViewById( R.id.et7_5Comment );
        et8 = findViewById( R.id.et8 );
        et8Qty = findViewById( R.id.et8Qty );
        et8Comment = findViewById( R.id.et8Comment );
        et85 = findViewById( R.id.et8_5 );
        et85Qty = findViewById( R.id.et8_5Qty );
        et85Comment = findViewById( R.id.et8_5Comment );
        tongue = findViewById( R.id.tongue );
        tongueQty = findViewById( R.id.tongueQty );
        tongueComment = findViewById( R.id.tongueComment );
        childHolder = findViewById( R.id.childHolder );
        childHolderQty = findViewById( R.id.childHolderQty );
        childHolderComment = findViewById( R.id.childHolderComment );
        tubeTape = findViewById( R.id.tubeTape );
        tubeTapeQty = findViewById( R.id.tubeTapeQty );
        tubeTapeComment = findViewById( R.id.tubeTapeComment );
        spareBatteries = findViewById( R.id.spareBatteries );
        spareBatteriesQty = findViewById( R.id.spareBatteriesQty );
        spareBatteriesComment = findViewById( R.id.spareBatteriesComment );
        micropore = findViewById( R.id.micropore );
        microporeQty = findViewById( R.id.microporeQty );
        microporeComment = findViewById( R.id.microporeComment );
        peepValve = findViewById( R.id.peepValve );
        peepValveQty = findViewById( R.id.peepValveQty );
        peepValveComment = findViewById( R.id.peepValveComment );
        spaceBlanket = findViewById( R.id.spaceBlanket );
        spaceBlanketQty = findViewById( R.id.spaceBlanketQty );
        spaceBlanketComment = findViewById( R.id.spaceBlanketComment );
        gauze = findViewById( R.id.gauze );
        gauzeQty = findViewById( R.id.gauzeQty );
        gauzeComment = findViewById( R.id.gauzeComment );
        triBandages = findViewById( R.id.triBandages );
        triBandageseQty = findViewById( R.id.triBandageseQty );
        triBandagesComment = findViewById( R.id.triBandagesComment );
        vommit = findViewById( R.id.vommit );
        vommitQty = findViewById( R.id.vommitQty );
        vommitComment = findViewById( R.id.vommitComment );
        lastlin = findViewById( R.id.lastlin );
        multiPack = findViewById( R.id.multiPack );
        multiPackQty = findViewById( R.id.multiPackQty );
        multiPackComment = findViewById( R.id.multiPackComment );*/


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
        next = findViewById( R.id.next );
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

        TableSync();
        TimePicker();
    }

    Context mContext=this;

    private void TimePicker()
    {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        inspectionTime.setOnClickListener(view -> {

            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (view1, hourOfDay, minute1) -> inspectionTime.setText(hourOfDay + ":" + minute1),hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
            timePickerDialog.show();
        });

    }
    private void TableSync()
    {
        root = findViewById(R.id.content);
        root2 = findViewById(R.id.content2);
        root3 = findViewById(R.id.content3);
        root4 = findViewById(R.id.content4);
        root5 = findViewById(R.id.content5);
        root6 = findViewById(R.id.content6);
        //root7 = findViewById(R.id.content7);

        View table = getVehicleTable();
        View table2 = getEquipmentTable();
        View table3 = getDisposableTable();
        View table4 = getDrugTable();
        View table5 = getIlsDrugTable();
        View table6 = getDocumentationTable();
        //View table7 = getDiagnosticsTable();

        root.addView(table);
        root2.addView(table2);
        root3.addView(table3);
        root4.addView(table4);
        root5.addView(table5);
        root6.addView(table6);
        //root7.addView(table7);
    }


    public void Send(View v){

        if(validate()){
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            //we are connected to a network
            connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
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
                responseServer = Login_Page.InputStreamToStringExample.getStringFromInputStream(inputStream);
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
    public boolean validate(){

        //check if the edits at the to are empty
        boolean valid = true;

        if(driver.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter the Driver's name ", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if(controller.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter the Controller's name ", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if(checkedBy.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter the checker's name ", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if(inspectionTime.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter the inspection time ", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        if (valid){
            try {
                String time = inspectionTime.getText().toString();
                time = time.trim();
                DateFormat sdf = new SimpleDateFormat("hh:mm");
                Date date = sdf.parse(time);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"Please ensure that the inspection time is in 24 hour format", Toast.LENGTH_SHORT).show();
                valid = false;
            }
        }

        // Vehicle

        if(valid){
            if (!air.isChecked() && airComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that air conditioner is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!antennae.isChecked() && antennaeComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that antennae is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!battery.isChecked() && batteryComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that battery secured is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!body.isChecked() && bodyComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that body work is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!brake.isChecked() && brakeComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that brake lights is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!branding.isChecked() && brandingComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that branding is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!dashboard.isChecked() && dashboard.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that dashboard lights is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!emergency.isChecked() && emergencyComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that emergency is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!exhaust.isChecked() && exhaustComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that exhaust is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!oil.isChecked() && oilComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that oil level is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!fuel.isChecked() && fuelComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that fuel level is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!headlights.isChecked() && headlightsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that headlights is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!leftIndicator.isChecked() && leftIndicatorComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that left indicator is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!rightIndicator.isChecked() && rightIndicatorComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that right indicator is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!back.isChecked() && backComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that interior light back is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!front.isChecked() && frontComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that interior light front is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!jack.isChecked() && jackComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that jack and tools is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!licence.isChecked() && licenceComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that licence is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!leds.isChecked() && ledsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that LED lights is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!plates.isChecked() && platesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that number plates is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!radio.isChecked() && radioComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that radio is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!rear.isChecked() && rear.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that rear view mirror is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!reverse.isChecked() && reverseComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that reverse lights is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!side.isChecked() && sideComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that side mirror is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!siren.isChecked() && sirenComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that siren is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!spare.isChecked() && spareComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that spare wheel is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!tread.isChecked() && treadComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that tyre tread is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!pressure.isChecked() && pressureComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that tyre pressure is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!windows.isChecked() && windowsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that windows is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!windscreen.isChecked() && windscreenComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that windscreen is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        //Equipment 1

        if(valid){
            if (!ecg.isChecked() && ecgComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ECG is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!adultEcg.isChecked() && adultEcgComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adult ECG is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!childEcg.isChecked() && childEcgComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that paed ECG is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!spareBattery.isChecked() && spareBatteryComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that spare battery is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!spareEcg.isChecked() && spareEcgComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that spare ecg is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!oximeter.isChecked() && oximeterComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that pulse oximeter is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!suctionUnit.isChecked() && suctionUnitComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that suction unit is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!suctionReservoir.isChecked() && suctionReservoirComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that suction reservoir is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!chargingCable.isChecked() && chargingCableComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that charging cable is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!suctionTubing.isChecked() && suctionTubingComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that suction tubing is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!softSuction.isChecked() && softSuctionComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that soft suction catheters is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adultSuction.isChecked() && adultSuctionComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adult suction catheters is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!paedSuction.isChecked() && paedSuctionComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that paed suction catheters is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!ventilatorKit.isChecked() && ventilatorKitComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that mechanical ventilator is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!circuitHose.isChecked() && circuitHoseComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that circuit hose is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!circuitHoseFilter.isChecked() && circuitHoseFilterComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that circuit hose filter is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!co2Attachment.isChecked() && co2AttachmentComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ETCO2 Attachment is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!oxygenCylinder.isChecked() && oxygenCylinderComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that portable oxygen cylinder is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!pressure.isChecked() && pressureComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that high pressure oxygen gauge is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!oxygenCylinderKey.isChecked() && oxygenCylinderKeyComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that oxygen cylinder key spanner is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!syringePump.isChecked() && syringePumpComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that B Braun syringe pump is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!powerCable.isChecked() && powerCableComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that syringe pump power cable is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!syringe50.isChecked() && syringe50Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that B Braun 50ml syringe is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!microbore.isChecked() && microboreComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that microbore extension set is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!stretcher.isChecked() && stretcherComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that stretcher is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!straps.isChecked() && strapsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that stretcher straps is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!mattress.isChecked() && mattressComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that stretcher matress is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!sheet.isChecked() && sheetComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that sheet is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!pillow.isChecked() && pillowComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that pillow is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!pillowCase.isChecked() && pillowCaseComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that pillow case is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!blanket.isChecked() && blanketComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that blanket is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!other.isChecked() && otherComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that other is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adultKed.isChecked() && adultKedComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adult KED is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!childKed.isChecked() && childKedComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that child KED is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adultSplint.isChecked() && adultSplintComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adult traction splint is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!scoopStretcher.isChecked() && scoopStretcher.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that scoop stretcher is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!headBlocks.isChecked() && headBlocksComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that head blocks is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!basePlate.isChecked() && basePlateComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that base plate is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!spiderHarness.isChecked() && spiderHarnessComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that spider harness is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!headStraps.isChecked() && headStrapsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that head straps is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!zipStretcher.isChecked() && zipStretcherComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that zip stretcher is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!longSplints.isChecked() && longSplints.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that long splints is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!shortSplints.isChecked() && shortSplintsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that short splints is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adultCollar.isChecked() && adultCollarComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that c-collar(adult) is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!childCollar.isChecked() && childCollarComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that c-collar(paed) is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!fireExtinguisher.isChecked() && fireExtinguisherComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that fire extinguisher is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!rescueHelmet.isChecked() && rescueHelmet.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that rescue helmet is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!roadCones.isChecked() && roadConesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that collapsble road cones is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!reflectorVests.isChecked() && reflectorVestsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that body work is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!body.isChecked() && bodyComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that body work is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!wasteBin.isChecked() && wasteBinComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that waste bin is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!medicalWaste.isChecked() && medicalWasteComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that medical waste bag is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!sharpsBin.isChecked() && sharpsBinComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that bo is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!body.isChecked() && bodyComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that body work is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!gloves.isChecked() && glovesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that gloves(extra) is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        //Disposables

        if(valid){
            if (!portableOxygen.isChecked() && portableOxygenComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that portable oxygen is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!pinRegulator.isChecked() && pinRegulatorComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that pin index regulator is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!oxygenMainline.isChecked() && oxygenMainlineComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that oxygen mainline is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!oxygenFlowMeter.isChecked() && oxygenFlowMeterComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that oxygen flow meter is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!bullNose.isChecked() && bullNoseComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that bullnose regulator is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!aed.isChecked() && aedComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that AED is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adultDefibPads.isChecked() && adultDefibPadsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adult defib pads is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!childDefibPads.isChecked() && childDefibPadsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that paed defib pads is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!defibGel.isChecked() && defibGelComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that defib gel is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        //Drugs Bag

        if(valid){
            if (!asprin.isChecked() && asprinComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that asprin is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!gtn.isChecked() && gtnComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that gtn is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!clopidogrel.isChecked() && clopidogrelComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that clopidogrel is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adenosine.isChecked() && adenosineComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adenosine is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!amiodarone.isChecked() && amiodarone.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that amiodarone is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!lignocaine.isChecked() && lignocaineComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that lignocaine is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adrenaline.isChecked() && adrenalineComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adrenaline is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!atropine.isChecked() && atropineComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that atropine is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!magnesium.isChecked() && magnesiumComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that magnesium sulphate is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!calcium.isChecked() && calciumComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that calcium chloride is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!soda.isChecked() && sodaComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that soda BIC is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!thiamine.isChecked() && thiamineComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that thiamine is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!diazepam.isChecked() && diazepamComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that diazepam is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!midazolam.isChecked() && midazolamComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that midazolam is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!promethazine.isChecked() && promethazineComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that promethazine is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!activatedCharcoal.isChecked() && activatedCharcoalComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that activated charcoal is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!flumazenil.isChecked() && flumazenilComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that flumazenil is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!naloxone.isChecked() && naloxoneComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that naloxone is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!morphine.isChecked() && morphineComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that gtn is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!dextrose.isChecked() && dextroseComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that dextrose is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!glucose.isChecked() && glucoseComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that oral glucose is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!fenoterol.isChecked() && fenoterolComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that fenoterol is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!bromide.isChecked() && bromideComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ipratropium bromide is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!corticosteriods.isChecked() && corticosteriodsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that corticosteroids is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!furosemide.isChecked() && furosemideComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that furosemide is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!metaclopramide.isChecked() && metaclopramideComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that metaclopramide is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!buscopan.isChecked() && buscopanComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that buscopan is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        //ILS Drug Pouch

        if(valid){
            if (!bigPlasters.isChecked() && bigPlastersComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that big platers is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!smallPlasters.isChecked() && smallPlastersComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that small plasters is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!cetrimide.isChecked() && cetrimide.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that cetrimide is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!elastoplast.isChecked() && elastoplastComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that elastoplast is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        //Documentation

        if(valid){
            if (!prf.isChecked() && prfComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that PRF Book is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!doa.isChecked() && doaComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that DOA Book is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!gop.isChecked() && gopComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that GOP Forms is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        //Equipment
        if(valid){
            if (!torniquet.isChecked() && torniquetComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that torniquet is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!glucometer.isChecked() && glucometerComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that glucometer is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!gloves2.isChecked() && gloves2Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that gloves is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!bp.isChecked() && bpComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that bp cuff is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!stethoscope.isChecked() && stethoscopeComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that stethoscope is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!rescueScissors.isChecked() && rescueScissorsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that rescue scissors is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!thermometer.isChecked() && thermometerComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that thermometer is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!pupilTorch.isChecked() && pupilTorchComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that pupul torch is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!testStrips.isChecked() && testStripsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that test strips is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!lancets.isChecked() && lancetsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that lancets is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!sats.isChecked() && satsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that sats probe is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adultMask.isChecked() && adultMaskComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adult venturi mask is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adultRebreather.isChecked() && adultRebreatherComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adult rebreather is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adultNebulizer.isChecked() && adultNebulizerComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adult nebulizer is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adultNasal.isChecked() && adultNasalComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adult nasal cannula is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!childMask.isChecked() && childMaskComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that child venturi mask is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!childRebreather.isChecked() && childRebreatherComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that child rebreather is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!childNebuliser.isChecked() && childNebuliserComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that child nebulizer is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!childNasal.isChecked() && childNasalComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that child nasal cannula is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!traumaPads.isChecked() && traumaPadsComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that trauma pads is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!traumaDressing100.isChecked() && traumaDressing100Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that trauma dressing 75x100mm is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!traumaDressing200.isChecked() && traumaDressing200Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that trauma dressing 150x200mm is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!bandage75.isChecked() && bandage75Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that conforming bandage 75mm is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!bandage50.isChecked() && bandage50Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that conforming bandage 50mm is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!stretchBandage.isChecked() && stretchBandageComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that stretch bandage 200x300mm is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!elastoplast.isChecked() && elastoplastComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that conforming elastoplast is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adultBvm.isChecked() && adultBvmComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adult bvm is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!childBvm.isChecked() && childBvmComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that child bvm is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!infantBvm.isChecked() && infantBvmComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that infant bvm is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!adultMagilles.isChecked() && adultMagillesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that adult magilles is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!childMagilles.isChecked() && childMagillesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that paed magilles is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!tube000.isChecked() && tube000Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that op tube 000 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!tube00.isChecked() && tube00Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that op tube 00 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!tube0.isChecked() && tube0Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that op tube 0 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!tube1.isChecked() && tube1Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that op tube 1 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!tube2.isChecked() && tube2Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that op tube 2 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!tube3.isChecked() && tube3Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that op tube 3 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!tube4.isChecked() && tube4Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that op tube 4 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!tube5.isChecked() && tube5Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that op tube 5 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!lma5.isChecked() && lma5Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that lma 5 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!lma4.isChecked() && lma4Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that lma 4 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!lma3.isChecked() && lma3Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that lma 3 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!lma2.isChecked() && lma2Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that lma 2 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!lma1.isChecked() && lma1Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that lma 1 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!uvcPack.isChecked() && uvcPackComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that UVC pack is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!maternity.isChecked() && maternityComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that maternity pack is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!needleCric.isChecked() && needleCricComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that needle cric pack is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!ngTube.isChecked() && ngTubeComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ng tube is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!airwayPack.isChecked() && airwayPackComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that surgical airway pack is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!urinaryCatheter.isChecked() && urinaryCatheterComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that urinary catheter is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!urineBag.isChecked() && urineBagComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that urine bag is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!suturePack.isChecked() && suturePackComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that suture pack is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!spirometer.isChecked() && spirometerComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that spirometer is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!ringers1000.isChecked() && ringers1000Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ringers 1000ml is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!sodiumChloride.isChecked() && sodiumChlorideComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that sodium chloride is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!colloid.isChecked() && colloidComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that colloid is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!canulla14.isChecked() && canulla14Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 14g IV cannula is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!canulla16.isChecked() && canulla16Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 16g IV cannula is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!canulla18.isChecked() && canulla18Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 18g IV cannula is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!canulla20.isChecked() && canulla20Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 20g IV cannula is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!canulla22.isChecked() && canulla22Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 22g IV cannula is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!canulla24.isChecked() && canulla24Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 24g IV cannula is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!admin60.isChecked() && admin60Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 60 drop admin set is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!admin20.isChecked() && admin20Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 20 drop admin set is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!admin15.isChecked() && admin15Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 15 drop admin set is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!admin10.isChecked() && admin10Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 10 drop admin set is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!webcol.isChecked() && webcol.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that webcol is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!tegaderm.isChecked() && tegadermComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that tegaderm is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!syringe50.isChecked() && syringe50Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that syringes 50ml is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!syringe20.isChecked() && syringe20Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that syringes 20ml is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!syringe10.isChecked() && syringe10Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that syringes 10ml is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!syringe5.isChecked() && syringe5Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that syringes 5ml is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!syringe3.isChecked() && syringe3Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that syringes 3ml is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!needle18.isChecked() && needle18Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 18g hypodermic needle is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!needle21.isChecked() && needle21Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that 21g hypodermic needle is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!handle.isChecked() && handleComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that laryngoscope handle is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!blade4.isChecked() && blade4Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that laryngoscope blade 4 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!blade3.isChecked() && blade3Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that laryngoscope blade 3 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!blade2.isChecked() && blade2Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that laryngoscope blade 2 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!blade1.isChecked() && blade1Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that laryngoscope blade 1 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!blade0.isChecked() && blade0Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that laryngoscope blade 0 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et25.isChecked() && et25Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 2.5 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et3.isChecked() && et3Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 3 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et35.isChecked() && et35Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 3.5 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et4.isChecked() && et4Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 4 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et45.isChecked() && et45Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 4.5 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et5.isChecked() && et5Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 5 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et55.isChecked() && et55Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 5.5 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et6.isChecked() && et6Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 6 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et65.isChecked() && et65Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 6.5 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et7.isChecked() && et7Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 7 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et75.isChecked() && et75Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 7.5 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et8.isChecked() && et8Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 8 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!et85.isChecked() && et85Comment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that ET 8.5 is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!tongue.isChecked() && tongueComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that tongue depressors is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!childHolder.isChecked() && childHolderComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that paed tube holder is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!tubeTape.isChecked() && tubeTapeComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that tube tape is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!spareBatteries.isChecked() && spareBatteriesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that spare batteries is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!micropore.isChecked() && microporeComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that micropore is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!peepValve.isChecked() && peepValveComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that peep valve is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!spaceBlanket.isChecked() && spaceBlanketComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that space blanket is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!gauze.isChecked() && gauzeComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that gauze is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!triBandages.isChecked() && triBandagesComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that triangular bandages is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }


        if(valid){
            if (!vommit.isChecked() && vommitComment.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure vomit bags is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }

        if(valid){
            if (!multiPack.isChecked() && multiPack.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getApplicationContext(),"Please ensure that burnshield multipack is checked or the comment is filled in ", Toast.LENGTH_SHORT).show();
            }

        }




        return valid;
    }

    private View getVehicleTable(){

        // create table
        TableLayout table = new TableLayout(this);

        int parentWidth = Row.getDisplayMatrix(this).widthPixels;

        //int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        //int heightSpec = View.MeasureSpec.makeMeasureSpec(parentHeight, View.MeasureSpec.EXACTLY);

        //root.measure(widthSpec, heightSpec);

        //parentWidth = root.getMeasuredWidth();
        //parentHeight = root.getMeasuredHeight();

        // create table LayoutParams
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(MatchParent, WrapContent);
        table.setLayoutParams(tableParams);
        table.setWeightSum(50);

        //table.setBackgroundColor(Color.WHITE);

        // create the heading row
        TableRow headingRow = new TableRow(this);

        // create heading LayoutParams
        TableRow.LayoutParams headingParams = new TableRow.LayoutParams();
        headingParams.weight = 2;

        headingRow.setLayoutParams(headingParams);
        headingRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        //headingRow.setWeightSum(10);
        //headingRow.setBackground(getResources().getDrawable(R.drawable.cell_shape));

        headingRow.setBackgroundColor(Color.parseColor("#ff33b5e5"));

        //heading 1
        TextView heading1 = new TextView(this);
        heading1.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading1Params = new TableRow.LayoutParams(parentWidth * 7/20, Row.dpToPixels(50,this));
        int px5 = Row.dpToPixels(15,this);
        //heading1Params.setMargins(px5,px5,px5,0);
        //heading1Params.column = 0;
        //heading1.setLayoutParams(new ViewGroup.LayoutParams(heading1Params));
        heading1.setText("Response Car M05");
        heading1.setTextColor(Color.WHITE);
        heading1.setAllCaps(true);
        heading1.setBackgroundResource(R.drawable.cell_shape);
        //heading1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        heading1.setTypeface(Typeface.DEFAULT_BOLD);
        headingRow.addView(heading1, heading1Params);

        //heading 2
        TextView heading2 = new TextView(this);
        heading2.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading2Params = new TableRow.LayoutParams(parentWidth * 2/20, MatchParent);
        //heading2Params.setMargins(px5,px5,px5,0);
        //heading2Params.column = 1;
        //heading2.setLayoutParams(new ViewGroup.LayoutParams(heading2Params));
        heading2.setText("QTY");
        heading2.setTextColor(Color.WHITE);
        heading2.setAllCaps(true);
        //heading2.TextAlignment = TextAlignment.Center;
        heading2.setBackgroundResource(R.drawable.cell_shape);
        heading2.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading2,heading2Params);

        //heading 2
        TextView heading3 = new TextView(this);
        heading3.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading3Params = new TableRow.LayoutParams(parentWidth * 4/20, MatchParent);
        //heading3Params.setMargins(px5,px5,px5,0);
        //heading3Params.column = 2;
        //heading3.setLayoutParams(new ViewGroup.LayoutParams(heading3Params));
        heading3.setText("Checked");
        heading3.setTextColor(Color.WHITE);
        heading3.setAllCaps(true);
        //heading3.TextAlignment = TextAlignment.Center;
        heading3.setBackgroundResource(R.drawable.cell_shape);
        heading3.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading3, heading3Params);


        //heading 2
        TextView heading4 = new TextView(this);
        heading4.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading4Params = new TableRow.LayoutParams(parentWidth * 7/20, MatchParent);
        //heading4Params.setMargins(px5,px5,px5,0);
        heading4Params.column = 3;
        //heading4.setLayoutParams(new ViewGroup.LayoutParams(heading4Params));
        heading4.setText("Comments");
        heading4.setTextColor(Color.WHITE);
        heading4.setAllCaps(true);
        //heading4.TextAlignment = TextAlignment.Center;
        heading4.setBackgroundResource(R.drawable.cell_shape);
        heading4.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading4, heading4Params);

        table.addView(headingRow);

        NestedScrollView nestedScrollView = new NestedScrollView(this);
        NestedScrollView.LayoutParams scrollParams = new NestedScrollView.LayoutParams(MatchParent, MatchParent);
        nestedScrollView.setLayoutParams(scrollParams);

        LinearLayout tableContent = new LinearLayout(this);
        LinearLayout.LayoutParams tableContentParams = new LinearLayout.LayoutParams(MatchParent, WrapContent);
        tableContent.setLayoutParams(tableContentParams);
        tableContent.setOrientation(LinearLayout.VERTICAL);

        String[] _list = new String[]{"AIRCONDITIONER","ANTENNAE\'S","BATTERY SECURED","BODY WORK","BRAKE LIGHTS","BRANDING","DASHBOARD LIGHTS",
                "EMERGENCY LIGHTS","EXHAUST","FUEL LEVEL","HEADLIGHTS","INDICATORS LEFT","INDICATORS RIGHT","INTERIOR LIGHT BACK","INTERIOR LIGHT FRONT",
                "JACK AND TOOLS","LED'S","LICENSE DISK X2 & EXP DATE","NUMBER PLATES","RADIO CD","REAR VIEW MIRROR","REVERSE LIGHTS","SIDE MIRRORS","SIREN",
                "SPARE WHEEL","TYRE THREAD","TYRE PRESSURE","WINDOWS","WINDSCREEN"
        };

        String[] qtySpinnerOptions = new String[]{"Okay", "No check", "Need Repair"};
        String[] fuelLevelSpinnerOptions = new String[]{"Full", "3/4", "1/2", "1/4", "Empty"};

        for(int i = 0; i < _list.length; i++)
        {
            String st = _list[i];
            Row item = new Row(this, st, "1", i == 9? fuelLevelSpinnerOptions : qtySpinnerOptions, "OKAY", i == 9? "FULL   3/4   1/2   1/4":"No COMMENT","Comment here" , i, parentWidth);

            //headingRow.setBackgroundResource(R.drawable.cell_shape);
            //table.addView(item.getRow());
            tableContent.addView(item.getRow());

        }

        nestedScrollView.addView(tableContent);
        table.addView(nestedScrollView);

        return table;
    }

    private View getEquipmentTable(){

        // create table
        TableLayout table = new TableLayout(this);

        int parentWidth = Row.getDisplayMatrix(this).widthPixels;

        //int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        //int heightSpec = View.MeasureSpec.makeMeasureSpec(parentHeight, View.MeasureSpec.EXACTLY);

        //root.measure(widthSpec, heightSpec);

        //parentWidth = root.getMeasuredWidth();
        //parentHeight = root.getMeasuredHeight();

        // create table LayoutParams
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(MatchParent, WrapContent);
        table.setLayoutParams(tableParams);
        table.setWeightSum(50);

        //table.setBackgroundColor(Color.WHITE);

        // create the heading row
        TableRow headingRow = new TableRow(this);

        // create heading LayoutParams
        TableRow.LayoutParams headingParams = new TableRow.LayoutParams();
        headingParams.weight = 2;

        headingRow.setLayoutParams(headingParams);
        headingRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        //headingRow.setWeightSum(10);
        //headingRow.setBackground(getResources().getDrawable(R.drawable.cell_shape));

        headingRow.setBackgroundColor(Color.parseColor("#ff33b5e5"));

        //heading 1
        TextView heading1 = new TextView(this);
        heading1.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading1Params = new TableRow.LayoutParams(parentWidth * 7/20, Row.dpToPixels(50,this));
        int px5 = Row.dpToPixels(15,this);
        //heading1Params.setMargins(px5,px5,px5,0);
        //heading1Params.column = 0;
        //heading1.setLayoutParams(new ViewGroup.LayoutParams(heading1Params));
        heading1.setText("DESCRIPTION");
        heading1.setTextColor(Color.WHITE);
        heading1.setAllCaps(true);
        heading1.setBackgroundResource(R.drawable.cell_shape);
        //heading1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        heading1.setTypeface(Typeface.DEFAULT_BOLD);
        headingRow.addView(heading1, heading1Params);

        //heading 2
        TextView heading2 = new TextView(this);
        heading2.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading2Params = new TableRow.LayoutParams(parentWidth * 2/20, MatchParent);
        //heading2Params.setMargins(px5,px5,px5,0);
        //heading2Params.column = 1;
        //heading2.setLayoutParams(new ViewGroup.LayoutParams(heading2Params));
        heading2.setText("QTY");
        heading2.setTextColor(Color.WHITE);
        heading2.setAllCaps(true);
        //heading2.TextAlignment = TextAlignment.Center;
        heading2.setBackgroundResource(R.drawable.cell_shape);
        heading2.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading2,heading2Params);

        //heading 2
        TextView heading3 = new TextView(this);
        heading3.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading3Params = new TableRow.LayoutParams(parentWidth * 4/20, MatchParent);
        //heading3Params.setMargins(px5,px5,px5,0);
        //heading3Params.column = 2;
        //heading3.setLayoutParams(new ViewGroup.LayoutParams(heading3Params));
        heading3.setText("Checked");
        heading3.setTextColor(Color.WHITE);
        heading3.setAllCaps(true);
        //heading3.TextAlignment = TextAlignment.Center;
        heading3.setBackgroundResource(R.drawable.cell_shape);
        heading3.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading3, heading3Params);


        //heading 2
        TextView heading4 = new TextView(this);
        heading4.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading4Params = new TableRow.LayoutParams(parentWidth * 7/20, MatchParent);
        //heading4Params.setMargins(px5,px5,px5,0);
        heading4Params.column = 3;
        //heading4.setLayoutParams(new ViewGroup.LayoutParams(heading4Params));
        heading4.setText("Comments");
        heading4.setTextColor(Color.WHITE);
        heading4.setAllCaps(true);
        //heading4.TextAlignment = TextAlignment.Center;
        heading4.setBackgroundResource(R.drawable.cell_shape);
        heading4.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading4, heading4Params);

        table.addView(headingRow);

        NestedScrollView nestedScrollView = new NestedScrollView(this);
        NestedScrollView.LayoutParams scrollParams = new NestedScrollView.LayoutParams(MatchParent, MatchParent);
        nestedScrollView.setLayoutParams(scrollParams);

        LinearLayout tableContent = new LinearLayout(this);
        LinearLayout.LayoutParams tableContentParams = new LinearLayout.LayoutParams(MatchParent, WrapContent);
        tableContent.setLayoutParams(tableContentParams);
        tableContent.setOrientation(LinearLayout.VERTICAL);

        String[] _list = new String[]{"LAERDAL SUCTION UNIT+TUBING","YANKUAR CATHETER AND SOFT TIP","ECG MONITOR + ELECTRODES+ DEFIB PADS","SMALL CERVICAL COLLARS",
                "LARGE CERVICAL COLLARS","LONG KRAMER WIRE SPLINTS","SHORT KRAMER WIRE SPLINTS","REGULATOR PIN INDEX CM1201","PORTABLE 02 HC012094",
                "SHARPS CONTAINER 1228","REFLECTIVE VESTS","RESCUE HELMET","LINEN SAVERS","FIRE EXTINGUISHER","KED ADULT","ADULT TRACTION SPLINT",
                "EMERGENCY TRIANGLE","ROAD CONES","TORNIQUET","GLUCOMETER","GLOVES","BP CUFF","STETHOSCOPE","RESCUE SCISSORS","THERMOMETER","PUPIL TORCH","TEST STRIPS","LANCETS",
                "SATS PROBE","VENTURI MASK ADULT","REBREATHER MASK ADULT","NEBULIZER MASK ADULT","NASAL CANNULA ADULT","VENTURI MASK CHILD","REBREATHER MASK CHILD","NEBULISER MASK CHILD",
                "NASAL CANNULA CHILD","TRAUMA PADS","TRAUMA DRESSING 75X100MM","TRAUMA DRESSING 150X200MM","CONFORMING BANDAGE 75MM","CONFORMING BANDAGE 50MM","STRECTH BANDAGE 200X300MM",
                "ELASTOPLAST","BVM ADULT+RESERVOIR","BVM CHILD+RESERVOIR","BVM INFANT+RESERVOIR","MAGILLES ADULT","MAGILLES PAED","OP TUBE SIZE 000","OP TUBE SIZE 00","OP TUBE SIZE 0",
                "OP TUBE SIZE 1","OP TUBE SIZE 2","OP TUBE SIZE 3","OP TUBE SIZE 4","OP TUBE SIZE 5","LMA 5","LMA 4","LMA 3","LMA 2","LMA 1","UVC PACK","MATERNITY PACK","NEEDLE CRIC PACK",
                "NG TUBE","SURGICAL AIRWAY PACK","URINARY CATHETER","URINE BAG","SUTURE PACK","SPIROMETER","RINGERS 1000ML","SODIUM CHLORIDE 0,9% 200ML","16G JELCO","18G JELCO","20G JELCO","22G JELCO",
                "24G JELCO","60 DROP ADMIN SET","20 DROP ADMIN SET","15 DROP ADMIN SET","10 DROP ADMIN SET","WEBCOL","TEGADERM","SYRINGES 20ML","SYRINGES 10ML","SYRINGES 5ML","SYRINGES 3ML","18G HYPODERMIC NEEDLE",
                "21G HYPODERMIC NEEDLE","LARYNGOSOPE HANDLE","LARYNGOSOPE BLADE 4","LARYNGOSOPE BLADE 3","LARYNGOSOPE BLADE 2","LARYNGOSOPE BLADE 1","LARYNGOSOPE BLADE 0","ET 2.5","ET 3.0",
                "ET 3.5","ET 4.0","ET 4.5","ET 5.0","ET 5.5","ET 6.0","ET 6.5","ET 7.0","ET 7.5","ET 8.0","ET 8.5","TONGUE DEPRESSORS","PEAD TUBE HOLDERS","TUBE TAPE","BATTERIES (SPARE)","MICROPORE",
                "PEEP VALVE","SPACE BLANKET","GAUZE","TRIANGULAR BANDAGES","VOMMIT BAGS","BURNSHIELD MULTIPACK","OTHER"
        };

        String[] _qtylist = new String[]{"1","1","1","1","2","2","1","1","1","2","1","1","1","1","1","1","*","*","*","*","*","*","1","1","1","1","1","1","1","1","1","25","1","2","2","2","2","2","2","2","2","10",
                "2","2","2","2","2","3","1","1","1","*","*","2","2","2","2","2","2","2","2","1","1","1","1","1","1","1","1","1","1","1","1","2","1","1","2","4","4","4","4","4","4",
                "2","2","2","23","4","4","4","4","4","5","10","1","1","1","1","1","1","2","2","2","2","2","2","2","2","2","2","2","2","2","8","2","2","1","1","1","2","6","2","2","1"
        };

        String[] qtySpinnerOptions = new String[]{"Okay", "No check", "Need Repair", "Resupply"};

        for(int i = 0; i < _list.length; i++)
        {
            String st = _list[i];
            String qtyst = _qtylist[i];
            Row item = new Row(this, st, qtyst, qtySpinnerOptions, "OKAY","No COMMENT", "Comment here", i, parentWidth);

            //headingRow.setBackgroundResource(R.drawable.cell_shape);
            //table.addView(item.getRow());
            tableContent.addView(item.getRow());

        }

        nestedScrollView.addView(tableContent);
        table.addView(nestedScrollView);

        return table;
    }

    private View getDisposableTable(){

        // create table
        TableLayout table = new TableLayout(this);

        int parentWidth = Row.getDisplayMatrix(this).widthPixels;

        //int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        //int heightSpec = View.MeasureSpec.makeMeasureSpec(parentHeight, View.MeasureSpec.EXACTLY);

        //root.measure(widthSpec, heightSpec);

        //parentWidth = root.getMeasuredWidth();
        //parentHeight = root.getMeasuredHeight();

        // create table LayoutParams
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(MatchParent, WrapContent);
        table.setLayoutParams(tableParams);
        table.setWeightSum(50);

        //table.setBackgroundColor(Color.WHITE);

        // create the heading row
        TableRow headingRow = new TableRow(this);

        // create heading LayoutParams
        TableRow.LayoutParams headingParams = new TableRow.LayoutParams();
        headingParams.weight = 2;

        headingRow.setLayoutParams(headingParams);
        headingRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        //headingRow.setWeightSum(10);
        //headingRow.setBackground(getResources().getDrawable(R.drawable.cell_shape));

        headingRow.setBackgroundColor(Color.parseColor("#ff33b5e5"));

        //heading 1
        TextView heading1 = new TextView(this);
        heading1.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading1Params = new TableRow.LayoutParams(parentWidth * 7/20, Row.dpToPixels(50,this));
        int px5 = Row.dpToPixels(15,this);
        //heading1Params.setMargins(px5,px5,px5,0);
        //heading1Params.column = 0;
        //heading1.setLayoutParams(new ViewGroup.LayoutParams(heading1Params));
        heading1.setText("DESCRIPTION");
        heading1.setTextColor(Color.WHITE);
        heading1.setAllCaps(true);
        heading1.setBackgroundResource(R.drawable.cell_shape);
        //heading1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        heading1.setTypeface(Typeface.DEFAULT_BOLD);
        headingRow.addView(heading1, heading1Params);

        //heading 2
        TextView heading2 = new TextView(this);
        heading2.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading2Params = new TableRow.LayoutParams(parentWidth * 2/20, MatchParent);
        //heading2Params.setMargins(px5,px5,px5,0);
        //heading2Params.column = 1;
        //heading2.setLayoutParams(new ViewGroup.LayoutParams(heading2Params));
        heading2.setText("QTY");
        heading2.setTextColor(Color.WHITE);
        heading2.setAllCaps(true);
        //heading2.TextAlignment = TextAlignment.Center;
        heading2.setBackgroundResource(R.drawable.cell_shape);
        heading2.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading2,heading2Params);

        //heading 2
        TextView heading3 = new TextView(this);
        heading3.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading3Params = new TableRow.LayoutParams(parentWidth * 4/20, MatchParent);
        //heading3Params.setMargins(px5,px5,px5,0);
        //heading3Params.column = 2;
        //heading3.setLayoutParams(new ViewGroup.LayoutParams(heading3Params));
        heading3.setText("Checked");
        heading3.setTextColor(Color.WHITE);
        heading3.setAllCaps(true);
        //heading3.TextAlignment = TextAlignment.Center;
        heading3.setBackgroundResource(R.drawable.cell_shape);
        heading3.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading3, heading3Params);


        //heading 2
        TextView heading4 = new TextView(this);
        heading4.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading4Params = new TableRow.LayoutParams(parentWidth * 7/20, MatchParent);
        //heading4Params.setMargins(px5,px5,px5,0);
        heading4Params.column = 3;
        //heading4.setLayoutParams(new ViewGroup.LayoutParams(heading4Params));
        heading4.setText("Comments");
        heading4.setTextColor(Color.WHITE);
        heading4.setAllCaps(true);
        //heading4.TextAlignment = TextAlignment.Center;
        heading4.setBackgroundResource(R.drawable.cell_shape);
        heading4.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading4, heading4Params);

        table.addView(headingRow);

        NestedScrollView nestedScrollView = new NestedScrollView(this);
        NestedScrollView.LayoutParams scrollParams = new NestedScrollView.LayoutParams(MatchParent, MatchParent);
        nestedScrollView.setLayoutParams(scrollParams);

        LinearLayout tableContent = new LinearLayout(this);
        LinearLayout.LayoutParams tableContentParams = new LinearLayout.LayoutParams(MatchParent, WrapContent);
        tableContent.setLayoutParams(tableContentParams);
        tableContent.setOrientation(LinearLayout.VERTICAL);

        String[] _list = new String[]{"Portable Oxygen Cyliner","Pin index regulator","Oxygen Cylindar Key/Spanner","Oxygen Mainline Cylinder",
                "Oxygen flow meter","Bullnose/Pin Index regulator","AED (Charge working)","Defib pads - adult","Defib pads - paed","Defib gel"
        };

        String[] _qtylist = new String[]{"2","1","1","1","2","1","1","1","1","1"
        };

        String[] qtySpinnerOptions = new String[]{"Okay", "No check", "Need Repair", "Resupply"};

        for(int i = 0; i < _list.length; i++)
        {
            String st = _list[i];
            String qtyst = _qtylist[i];
            Row item = new Row(this, st, qtyst, qtySpinnerOptions, "OKAY","No COMMENT", "Comment here", i, parentWidth);

            //headingRow.setBackgroundResource(R.drawable.cell_shape);
            //table.addView(item.getRow());
            tableContent.addView(item.getRow());

        }

        nestedScrollView.addView(tableContent);
        table.addView(nestedScrollView);

        return table;
    }

    private View getDrugTable(){

        // create table
        TableLayout table = new TableLayout(this);

        int parentWidth = Row.getDisplayMatrix(this).widthPixels;

        //int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        //int heightSpec = View.MeasureSpec.makeMeasureSpec(parentHeight, View.MeasureSpec.EXACTLY);

        //root.measure(widthSpec, heightSpec);

        //parentWidth = root.getMeasuredWidth();
        //parentHeight = root.getMeasuredHeight();

        // create table LayoutParams
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(MatchParent, WrapContent);
        table.setLayoutParams(tableParams);
        table.setWeightSum(50);

        //table.setBackgroundColor(Color.WHITE);

        // create the heading row
        TableRow headingRow = new TableRow(this);

        // create heading LayoutParams
        TableRow.LayoutParams headingParams = new TableRow.LayoutParams();
        headingParams.weight = 2;

        headingRow.setLayoutParams(headingParams);
        headingRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        //headingRow.setWeightSum(10);
        //headingRow.setBackground(getResources().getDrawable(R.drawable.cell_shape));

        headingRow.setBackgroundColor(Color.parseColor("#ff33b5e5"));

        //heading 1
        TextView heading1 = new TextView(this);
        heading1.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading1Params = new TableRow.LayoutParams(parentWidth * 7/20, Row.dpToPixels(50,this));
        int px5 = Row.dpToPixels(15,this);
        //heading1Params.setMargins(px5,px5,px5,0);
        //heading1Params.column = 0;
        //heading1.setLayoutParams(new ViewGroup.LayoutParams(heading1Params));
        heading1.setText("DESCRIPTION");
        heading1.setTextColor(Color.WHITE);
        heading1.setAllCaps(true);
        heading1.setBackgroundResource(R.drawable.cell_shape);
        //heading1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        heading1.setTypeface(Typeface.DEFAULT_BOLD);
        headingRow.addView(heading1, heading1Params);

        //heading 2
        TextView heading2 = new TextView(this);
        heading2.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading2Params = new TableRow.LayoutParams(parentWidth * 2/20, MatchParent);
        //heading2Params.setMargins(px5,px5,px5,0);
        //heading2Params.column = 1;
        //heading2.setLayoutParams(new ViewGroup.LayoutParams(heading2Params));
        heading2.setText("QTY");
        heading2.setTextColor(Color.WHITE);
        heading2.setAllCaps(true);
        //heading2.TextAlignment = TextAlignment.Center;
        heading2.setBackgroundResource(R.drawable.cell_shape);
        heading2.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading2,heading2Params);

        //heading 2
        TextView heading3 = new TextView(this);
        heading3.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading3Params = new TableRow.LayoutParams(parentWidth * 4/20, MatchParent);
        //heading3Params.setMargins(px5,px5,px5,0);
        //heading3Params.column = 2;
        //heading3.setLayoutParams(new ViewGroup.LayoutParams(heading3Params));
        heading3.setText("Checked");
        heading3.setTextColor(Color.WHITE);
        heading3.setAllCaps(true);
        //heading3.TextAlignment = TextAlignment.Center;
        heading3.setBackgroundResource(R.drawable.cell_shape);
        heading3.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading3, heading3Params);


        //heading 2
        TextView heading4 = new TextView(this);
        heading4.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading4Params = new TableRow.LayoutParams(parentWidth * 7/20, MatchParent);
        //heading4Params.setMargins(px5,px5,px5,0);
        heading4Params.column = 3;
        //heading4.setLayoutParams(new ViewGroup.LayoutParams(heading4Params));
        heading4.setText("Comments");
        heading4.setTextColor(Color.WHITE);
        heading4.setAllCaps(true);
        //heading4.TextAlignment = TextAlignment.Center;
        heading4.setBackgroundResource(R.drawable.cell_shape);
        heading4.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading4, heading4Params);

        table.addView(headingRow);

        NestedScrollView nestedScrollView = new NestedScrollView(this);
        NestedScrollView.LayoutParams scrollParams = new NestedScrollView.LayoutParams(MatchParent, MatchParent);
        nestedScrollView.setLayoutParams(scrollParams);

        LinearLayout tableContent = new LinearLayout(this);
        LinearLayout.LayoutParams tableContentParams = new LinearLayout.LayoutParams(MatchParent, WrapContent);
        tableContent.setLayoutParams(tableContentParams);
        tableContent.setOrientation(LinearLayout.VERTICAL);

        String[] _list = new String[]{"ASPRIN","GTN","CLOPIDOGREL","ADENOSINE","AMIODARONE","LIGNOCAINE2%(SYSTEMIC)","ADRENALINE","ATROPINE","MAG. SULPHATE",
                "CALCIUM CHLORIDE","SODA BIC 8.5%","THIAMINE(IM/SC)","DIAZEPAM","MIDAZOLAM","PROMETHAZINE","ACTIVATED CHARCOAL","FLUMAZENIL","NALOXONE",
                "MORPHINE","DEXTROSE 50%","ORAL GLUCOSE","FENOTEROL","IPRATROPRIUM BROMIDE","CORTICOSTERIODS","FUROSEMIDE","METACLOPRAMIDE","BUSCOPAN"
        };

        String[] _qtylist = new String[]{"6","1","8","3","3","2","10","4","3","2","1","1","2","2","2","1","1","4","2","2","2","3","3","3","3","4","2"
        };

        String[] qtySpinnerOptions = new String[]{"Okay", "No check", "Resupply"};

        for(int i = 0; i < _list.length; i++)
        {
            String st = _list[i];
            String qtyst = _qtylist[i];
            Row item = new Row(this, st, qtyst, qtySpinnerOptions, "OKAY","No COMMENT", "Comment here", i, parentWidth);

            //headingRow.setBackgroundResource(R.drawable.cell_shape);
            //table.addView(item.getRow());
            tableContent.addView(item.getRow());

        }

        nestedScrollView.addView(tableContent);
        table.addView(nestedScrollView);

        return table;
    }

    private View getIlsDrugTable(){

        // create table
        TableLayout table = new TableLayout(this);

        int parentWidth = Row.getDisplayMatrix(this).widthPixels;

        //int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        //int heightSpec = View.MeasureSpec.makeMeasureSpec(parentHeight, View.MeasureSpec.EXACTLY);

        //root.measure(widthSpec, heightSpec);

        //parentWidth = root.getMeasuredWidth();
        //parentHeight = root.getMeasuredHeight();

        // create table LayoutParams
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(MatchParent, WrapContent);
        table.setLayoutParams(tableParams);
        table.setWeightSum(50);

        //table.setBackgroundColor(Color.WHITE);

        // create the heading row
        TableRow headingRow = new TableRow(this);

        // create heading LayoutParams
        TableRow.LayoutParams headingParams = new TableRow.LayoutParams();
        headingParams.weight = 2;

        headingRow.setLayoutParams(headingParams);
        headingRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        //headingRow.setWeightSum(10);
        //headingRow.setBackground(getResources().getDrawable(R.drawable.cell_shape));

        headingRow.setBackgroundColor(Color.parseColor("#ff33b5e5"));

        //heading 1
        TextView heading1 = new TextView(this);
        heading1.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading1Params = new TableRow.LayoutParams(parentWidth * 7/20, Row.dpToPixels(50,this));
        int px5 = Row.dpToPixels(15,this);
        //heading1Params.setMargins(px5,px5,px5,0);
        //heading1Params.column = 0;
        //heading1.setLayoutParams(new ViewGroup.LayoutParams(heading1Params));
        heading1.setText("DESCRIPTION");
        heading1.setTextColor(Color.WHITE);
        heading1.setAllCaps(true);
        heading1.setBackgroundResource(R.drawable.cell_shape);
        //heading1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        heading1.setTypeface(Typeface.DEFAULT_BOLD);
        headingRow.addView(heading1, heading1Params);

        //heading 2
        TextView heading2 = new TextView(this);
        heading2.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading2Params = new TableRow.LayoutParams(parentWidth * 2/20, MatchParent);
        //heading2Params.setMargins(px5,px5,px5,0);
        //heading2Params.column = 1;
        //heading2.setLayoutParams(new ViewGroup.LayoutParams(heading2Params));
        heading2.setText("QTY");
        heading2.setTextColor(Color.WHITE);
        heading2.setAllCaps(true);
        //heading2.TextAlignment = TextAlignment.Center;
        heading2.setBackgroundResource(R.drawable.cell_shape);
        heading2.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading2,heading2Params);

        //heading 2
        TextView heading3 = new TextView(this);
        heading3.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading3Params = new TableRow.LayoutParams(parentWidth * 4/20, MatchParent);
        //heading3Params.setMargins(px5,px5,px5,0);
        //heading3Params.column = 2;
        //heading3.setLayoutParams(new ViewGroup.LayoutParams(heading3Params));
        heading3.setText("Checked");
        heading3.setTextColor(Color.WHITE);
        heading3.setAllCaps(true);
        //heading3.TextAlignment = TextAlignment.Center;
        heading3.setBackgroundResource(R.drawable.cell_shape);
        heading3.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading3, heading3Params);


        //heading 2
        TextView heading4 = new TextView(this);
        heading4.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading4Params = new TableRow.LayoutParams(parentWidth * 7/20, MatchParent);
        //heading4Params.setMargins(px5,px5,px5,0);
        heading4Params.column = 3;
        //heading4.setLayoutParams(new ViewGroup.LayoutParams(heading4Params));
        heading4.setText("Comments");
        heading4.setTextColor(Color.WHITE);
        heading4.setAllCaps(true);
        //heading4.TextAlignment = TextAlignment.Center;
        heading4.setBackgroundResource(R.drawable.cell_shape);
        heading4.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading4, heading4Params);

        table.addView(headingRow);

        NestedScrollView nestedScrollView = new NestedScrollView(this);
        NestedScrollView.LayoutParams scrollParams = new NestedScrollView.LayoutParams(MatchParent, MatchParent);
        nestedScrollView.setLayoutParams(scrollParams);

        LinearLayout tableContent = new LinearLayout(this);
        LinearLayout.LayoutParams tableContentParams = new LinearLayout.LayoutParams(MatchParent, WrapContent);
        tableContent.setLayoutParams(tableContentParams);
        tableContent.setOrientation(LinearLayout.VERTICAL);

        String[] _list = new String[]{"DEXTROSE 50%","FENETROL","IPRATROPIUM BROMIDE","DISPRIN","BIG PLASTERS","SMALL PLASTERS","CETRIMIDE 1% BOTTLE","GLUCOGEL","ELASTOPLAST",
        };

        String[] _qtylist = new String[]{"1","3","3","6","5","10","1","2","1"
        };

        String[] qtySpinnerOptions = new String[]{"Okay", "No check", "Resupply"};

        for(int i = 0; i < _list.length; i++)
        {
            String st = _list[i];
            String qtyst = _qtylist[i];
            Row item = new Row(this, st, qtyst, qtySpinnerOptions, "OKAY","No COMMENT", "Comment here", i, parentWidth);

            //headingRow.setBackgroundResource(R.drawable.cell_shape);
            //table.addView(item.getRow());
            tableContent.addView(item.getRow());

        }

        nestedScrollView.addView(tableContent);
        table.addView(nestedScrollView);

        return table;
    }

    private View getDocumentationTable(){

        // create table
        TableLayout table = new TableLayout(this);

        int parentWidth = Row.getDisplayMatrix(this).widthPixels;

        //int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        //int heightSpec = View.MeasureSpec.makeMeasureSpec(parentHeight, View.MeasureSpec.EXACTLY);

        //root.measure(widthSpec, heightSpec);

        //parentWidth = root.getMeasuredWidth();
        //parentHeight = root.getMeasuredHeight();

        // create table LayoutParams
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(MatchParent, WrapContent);
        table.setLayoutParams(tableParams);
        table.setWeightSum(50);

        //table.setBackgroundColor(Color.WHITE);

        // create the heading row
        TableRow headingRow = new TableRow(this);

        // create heading LayoutParams
        TableRow.LayoutParams headingParams = new TableRow.LayoutParams();
        headingParams.weight = 2;

        headingRow.setLayoutParams(headingParams);
        headingRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        //headingRow.setWeightSum(10);
        //headingRow.setBackground(getResources().getDrawable(R.drawable.cell_shape));

        headingRow.setBackgroundColor(Color.parseColor("#ff33b5e5"));

        //heading 1
        TextView heading1 = new TextView(this);
        heading1.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading1Params = new TableRow.LayoutParams(parentWidth * 7/20, Row.dpToPixels(50,this));
        int px5 = Row.dpToPixels(15,this);
        //heading1Params.setMargins(px5,px5,px5,0);
        //heading1Params.column = 0;
        //heading1.setLayoutParams(new ViewGroup.LayoutParams(heading1Params));
        heading1.setText("DESCRIPTION");
        heading1.setTextColor(Color.WHITE);
        heading1.setAllCaps(true);
        heading1.setBackgroundResource(R.drawable.cell_shape);
        //heading1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        heading1.setTypeface(Typeface.DEFAULT_BOLD);
        headingRow.addView(heading1, heading1Params);

        //heading 2
        TextView heading2 = new TextView(this);
        heading2.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading2Params = new TableRow.LayoutParams(parentWidth * 2/20, MatchParent);
        //heading2Params.setMargins(px5,px5,px5,0);
        //heading2Params.column = 1;
        //heading2.setLayoutParams(new ViewGroup.LayoutParams(heading2Params));
        heading2.setText("QTY");
        heading2.setTextColor(Color.WHITE);
        heading2.setAllCaps(true);
        //heading2.TextAlignment = TextAlignment.Center;
        heading2.setBackgroundResource(R.drawable.cell_shape);
        heading2.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading2,heading2Params);

        //heading 2
        TextView heading3 = new TextView(this);
        heading3.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading3Params = new TableRow.LayoutParams(parentWidth * 4/20, MatchParent);
        //heading3Params.setMargins(px5,px5,px5,0);
        //heading3Params.column = 2;
        //heading3.setLayoutParams(new ViewGroup.LayoutParams(heading3Params));
        heading3.setText("Checked");
        heading3.setTextColor(Color.WHITE);
        heading3.setAllCaps(true);
        //heading3.TextAlignment = TextAlignment.Center;
        heading3.setBackgroundResource(R.drawable.cell_shape);
        heading3.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading3, heading3Params);


        //heading 2
        TextView heading4 = new TextView(this);
        heading4.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading4Params = new TableRow.LayoutParams(parentWidth * 7/20, MatchParent);
        //heading4Params.setMargins(px5,px5,px5,0);
        heading4Params.column = 3;
        //heading4.setLayoutParams(new ViewGroup.LayoutParams(heading4Params));
        heading4.setText("Comments");
        heading4.setTextColor(Color.WHITE);
        heading4.setAllCaps(true);
        //heading4.TextAlignment = TextAlignment.Center;
        heading4.setBackgroundResource(R.drawable.cell_shape);
        heading4.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading4, heading4Params);

        table.addView(headingRow);

        NestedScrollView nestedScrollView = new NestedScrollView(this);
        NestedScrollView.LayoutParams scrollParams = new NestedScrollView.LayoutParams(MatchParent, MatchParent);
        nestedScrollView.setLayoutParams(scrollParams);

        LinearLayout tableContent = new LinearLayout(this);
        LinearLayout.LayoutParams tableContentParams = new LinearLayout.LayoutParams(MatchParent, WrapContent);
        tableContent.setLayoutParams(tableContentParams);
        tableContent.setOrientation(LinearLayout.VERTICAL);

        String[] _list = new String[]{"PRF BOOK","DOA BOOK","GOP FORMS"
        };

        String[] _qtylist = new String[]{"1","1","5"
        };

        String[] qtySpinnerOptions = new String[]{"Okay", "No check", "Resupply"};

        for(int i = 0; i < _list.length; i++)
        {
            String st = _list[i];
            String qtyst = _qtylist[i];
            Row item = new Row(this, st, qtyst, qtySpinnerOptions, "OKAY","No COMMENT","Comment here", i, parentWidth);

            //headingRow.setBackgroundResource(R.drawable.cell_shape);
            //table.addView(item.getRow());
            tableContent.addView(item.getRow());

        }

        nestedScrollView.addView(tableContent);
        table.addView(nestedScrollView);

        return table;
    }

    private View getDiagnosticsTable(){

        // create table
        TableLayout table = new TableLayout(this);

        int parentWidth = Row.getDisplayMatrix(this).widthPixels;

        //int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        //int heightSpec = View.MeasureSpec.makeMeasureSpec(parentHeight, View.MeasureSpec.EXACTLY);

        //root.measure(widthSpec, heightSpec);

        //parentWidth = root.getMeasuredWidth();
        //parentHeight = root.getMeasuredHeight();

        // create table LayoutParams
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(MatchParent, WrapContent);
        table.setLayoutParams(tableParams);
        table.setWeightSum(50);

        //table.setBackgroundColor(Color.WHITE);

        // create the heading row
        TableRow headingRow = new TableRow(this);

        // create heading LayoutParams
        TableRow.LayoutParams headingParams = new TableRow.LayoutParams();
        headingParams.weight = 2;

        headingRow.setLayoutParams(headingParams);
        headingRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        //headingRow.setWeightSum(10);
        //headingRow.setBackground(getResources().getDrawable(R.drawable.cell_shape));

        headingRow.setBackgroundColor(Color.parseColor("#ff33b5e5"));

        //heading 1
        TextView heading1 = new TextView(this);
        heading1.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading1Params = new TableRow.LayoutParams(parentWidth * 7/20, Row.dpToPixels(50,this));
        int px5 = Row.dpToPixels(15,this);
        //heading1Params.setMargins(px5,px5,px5,0);
        //heading1Params.column = 0;
        //heading1.setLayoutParams(new ViewGroup.LayoutParams(heading1Params));
        heading1.setText("DESCRIPTION");
        heading1.setTextColor(Color.WHITE);
        heading1.setAllCaps(true);
        heading1.setBackgroundResource(R.drawable.cell_shape);
        //heading1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        heading1.setTypeface(Typeface.DEFAULT_BOLD);
        headingRow.addView(heading1, heading1Params);

        //heading 2
        TextView heading2 = new TextView(this);
        heading2.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading2Params = new TableRow.LayoutParams(parentWidth * 2/20, MatchParent);
        //heading2Params.setMargins(px5,px5,px5,0);
        //heading2Params.column = 1;
        //heading2.setLayoutParams(new ViewGroup.LayoutParams(heading2Params));
        heading2.setText("QTY");
        heading2.setTextColor(Color.WHITE);
        heading2.setAllCaps(true);
        //heading2.TextAlignment = TextAlignment.Center;
        heading2.setBackgroundResource(R.drawable.cell_shape);
        heading2.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading2,heading2Params);

        //heading 2
        TextView heading3 = new TextView(this);
        heading3.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading3Params = new TableRow.LayoutParams(parentWidth * 4/20, MatchParent);
        //heading3Params.setMargins(px5,px5,px5,0);
        //heading3Params.column = 2;
        //heading3.setLayoutParams(new ViewGroup.LayoutParams(heading3Params));
        heading3.setText("Checked");
        heading3.setTextColor(Color.WHITE);
        heading3.setAllCaps(true);
        //heading3.TextAlignment = TextAlignment.Center;
        heading3.setBackgroundResource(R.drawable.cell_shape);
        heading3.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading3, heading3Params);


        //heading 2
        TextView heading4 = new TextView(this);
        heading4.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams heading4Params = new TableRow.LayoutParams(parentWidth * 7/20, MatchParent);
        //heading4Params.setMargins(px5,px5,px5,0);
        heading4Params.column = 3;
        //heading4.setLayoutParams(new ViewGroup.LayoutParams(heading4Params));
        heading4.setText("Comments");
        heading4.setTextColor(Color.WHITE);
        heading4.setAllCaps(true);
        //heading4.TextAlignment = TextAlignment.Center;
        heading4.setBackgroundResource(R.drawable.cell_shape);
        heading4.setTypeface(Typeface.DEFAULT_BOLD);

        headingRow.addView(heading4, heading4Params);

        table.addView(headingRow);

        NestedScrollView nestedScrollView = new NestedScrollView(this);
        NestedScrollView.LayoutParams scrollParams = new NestedScrollView.LayoutParams(MatchParent, MatchParent);
        nestedScrollView.setLayoutParams(scrollParams);

        LinearLayout tableContent = new LinearLayout(this);
        LinearLayout.LayoutParams tableContentParams = new LinearLayout.LayoutParams(MatchParent, WrapContent);
        tableContent.setLayoutParams(tableContentParams);
        tableContent.setOrientation(LinearLayout.VERTICAL);

        String[] _list = new String[]{"TORNIQUET","GLUCOMETER","GLOVES","BP CUFF","STETHOSCOPE","RESCUE SCISSORS","THERMOMETER","PUPIL TORCH","TEST STRIPS",
                "LANCETS","SATS PROBE"
        };

        String[] _qtylist = new String[]{"1","1","1","1","1","1","1","1","1","25","1"
        };

        String[] qtySpinnerOptions = new String[]{"Okay", "No check", "Resupply"};

        for(int i = 0; i < _list.length; i++)
        {
            String st = _list[i];
            String qtyst = _qtylist[i];
            Row item = new Row(this, st, qtyst, qtySpinnerOptions, "OKAY","No COMMENT","Comment here", i, parentWidth);

            //headingRow.setBackgroundResource(R.drawable.cell_shape);
            //table.addView(item.getRow());
            tableContent.addView(item.getRow());

        }

        nestedScrollView.addView(tableContent);
        table.addView(nestedScrollView);

        return table;
    }
}
