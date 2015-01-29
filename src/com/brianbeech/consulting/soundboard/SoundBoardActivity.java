package com.brianbeech.consulting.soundboard;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SoundBoardActivity extends Activity {

    Map<Integer, Integer> buttonRawMap = new HashMap<Integer, Integer>();
    MediaPlayer mediaPlayer;

    /*
    Load the button resources into a map
     */
    private void loadMap() {
        buttonRawMap.put(R.id.oh_billy, R.raw.oh_billy);
        buttonRawMap.put(R.id.baby_born, R.raw.baby_born);
//        buttonRawMap.put(R.id.brain_thee,R.raw.brain_thee);
        buttonRawMap.put(R.id.bribe, R.raw.bribe);
//        buttonRawMap.put(R.id.bungee_jumping,R.raw.bungee_jumping);
//        buttonRawMap.put(R.id.cable_guy,R.raw.cable_guy);
        buttonRawMap.put(R.id.decided_to_show, R.raw.decided_to_show);
        buttonRawMap.put(R.id.red_night, R.raw.down_red_night);
//        buttonRawMap.put(R.id.hitting_bottom,R.raw.hitting_bottom);
        buttonRawMap.put(R.id.i_hate_you, R.raw.i_hate_you);
        buttonRawMap.put(R.id.jerk_off, R.raw.jerk_off);
//        buttonRawMap.put(R.id.jerk,R.raw.jerking_chain);
        buttonRawMap.put(R.id.juice_ya_up, R.raw.juice_ya_up);
        buttonRawMap.put(R.id.lava, R.raw.lava);
        buttonRawMap.put(R.id.medieval_times, R.raw.medieval_times);
        buttonRawMap.put(R.id.mild_winters, R.raw.mild_winters);
        buttonRawMap.put(R.id.mug_of_ale, R.raw.mug_of_ale);
        buttonRawMap.put(R.id.perfectionist, R.raw.perfectionist);
        buttonRawMap.put(R.id.play_here, R.raw.playtoo);
        buttonRawMap.put(R.id.sooo, R.raw.sooo);
        buttonRawMap.put(R.id.summer_of_love, R.raw.summer_of_love);
        buttonRawMap.put(R.id.talk_to_me, R.raw.talk_to_me);
        buttonRawMap.put(R.id.tardy_one, R.raw.tardy_one);
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if (buttonRawMap.size() <= 0) {
            loadMap();
        }
        for (int btnId : buttonRawMap.keySet()) {
            ImageButton btn = (ImageButton) findViewById(btnId);
            registerForContextMenu(btn);
        }
        mediaPlayer = null;
    }

    /**
     * Method to create the context menus
     *
     * @param contextMenu
     * @param view
     * @param contextMenuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        contextMenu.setHeaderTitle("Sound Clip Actions");
        contextMenu.add(0, view.getId(), 0, "Set As Ringtone");
        contextMenu.add(0, view.getId(), 0, "Set As Notification");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.i("CableGuy", "Error: " + item.getItemId());
        if (item.getTitle().equals("Set As Ringtone")) {
            setTone(item.getItemId(), true);
            Toast.makeText(this, "Set as Ringtone", Toast.LENGTH_LONG).show();
        } else if (item.getTitle().equals("Set As Notification")) {
            setTone(item.getItemId(), false);
            Toast.makeText(this, "Set as Notification", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    /**
     * Turn off media player if paused
     */
    @Override
    public void onPause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        super.onPause();
    }

    /**
     * Turn off media player if stopped
     */
    @Override
    public void onStop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        super.onStop();
    }


    /**
     * Play a clip. If a clip is playing, stop it, case statement to play the new clip
     * @param view
     */
    public void playClip(View view) {
        ImageButton b = (ImageButton) view;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        switch (b.getId()) {
            case R.id.play_here:
                mediaPlayer = MediaPlayer.create(this, R.raw.playtoo);
                break;
            case R.id.oh_billy:
                mediaPlayer = MediaPlayer.create(this, R.raw.oh_billy);
                break;
            case R.id.woman_like_that:
                mediaPlayer = MediaPlayer.create(this, R.raw.woman_like_that);
                break;
            case R.id.talk_to_me:
                mediaPlayer = MediaPlayer.create(this, R.raw.talk_to_me);
                break;
            case R.id.wanna_hang_out:
                mediaPlayer = MediaPlayer.create(this, R.raw.wanna_hang_out);
                break;
            case R.id.decided_to_show:
                mediaPlayer = MediaPlayer.create(this, R.raw.decided_to_show);
                break;
            case R.id.tardy_one:
                mediaPlayer = MediaPlayer.create(this, R.raw.tardy_one);
                break;
            case R.id.jerk_off:
                mediaPlayer = MediaPlayer.create(this, R.raw.jerk_off);
                break;
            case R.id.i_hate_you:
                mediaPlayer = MediaPlayer.create(this, R.raw.i_hate_you);
                break;
            case R.id.red_night:
                mediaPlayer = MediaPlayer.create(this, R.raw.down_red_night);
                break;
            case R.id.juice_ya_up:
                mediaPlayer = MediaPlayer.create(this, R.raw.juice_ya_up);
                break;
            case R.id.bribe:
                mediaPlayer = MediaPlayer.create(this, R.raw.bribe);
                break;
            case R.id.mug_of_ale:
                mediaPlayer = MediaPlayer.create(this, R.raw.mug_of_ale);
                break;
            case R.id.medieval_times:
                mediaPlayer = MediaPlayer.create(this, R.raw.medieval_times);
                break;
            case R.id.baby_born:
                mediaPlayer = MediaPlayer.create(this, R.raw.baby_born);
                break;
            case R.id.summer_of_love:
                mediaPlayer = MediaPlayer.create(this, R.raw.summer_of_love);
                break;
            case R.id.mild_winters:
                mediaPlayer = MediaPlayer.create(this, R.raw.mild_winters);
                break;
            case R.id.sooo:
                mediaPlayer = MediaPlayer.create(this, R.raw.sooo);
                break;
            case R.id.perfectionist:
                mediaPlayer = MediaPlayer.create(this, R.raw.perfectionist);
                break;
            case R.id.lava:
                mediaPlayer = MediaPlayer.create(this, R.raw.lava);
                break;
        }
        try {
            mediaPlayer.start();
        } catch (IllegalStateException ise) {
            System.out.println("Error trying to play clip.");
        }
    }

    private boolean setTone(int soundId, boolean isRingtone) {
        byte[] buffer = null;
        InputStream fIn = getResources().openRawResource(buttonRawMap.get(soundId));
        int size = 0;

        try {
            size = fIn.available();
            buffer = new byte[size];
            fIn.read(buffer);
            fIn.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return false;
        }
        String storageLocation = isRingtone ? Environment.DIRECTORY_RINGTONES : Environment.DIRECTORY_NOTIFICATIONS;
        String fileName = isRingtone ? "/CableGuyRingtone.mp3" : "/CableGuyNotify.mp3";
        String path = Environment.getExternalStoragePublicDirectory(storageLocation).getPath();

        boolean exists = (new File(path)).exists();
        if (!exists) {
            new File(path).mkdirs();
        }

        FileOutputStream save;
        try {
            save = new FileOutputStream(path + fileName);
            save.write(buffer);
            save.flush();
            save.close();
        } catch (FileNotFoundException e) {
            Log.e("CableGuy", "Error - File not found: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            Log.e("CableGuy", "Error - IOE: " + e.getMessage());
            return false;
        }
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path + fileName)));

        File k = new File(path, fileName);

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, "CableGuyRingtone");
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/ogg");
        values.put(MediaStore.Audio.Media.ARTIST, "cssounds ");
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
        values.put(MediaStore.Audio.Media.IS_ALARM, true);
        values.put(MediaStore.Audio.Media.IS_MUSIC, false);

        //Insert it into the database
        Uri uri = MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath());
        getContentResolver().delete(uri, MediaStore.MediaColumns.DATA + "=\"" + k.getAbsolutePath() + "\"", null);
        Uri newUri = this.getContentResolver().insert(MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath()), values);
        if (isRingtone) {
            RingtoneManager.setActualDefaultRingtoneUri(
                    this,
                    RingtoneManager.TYPE_RINGTONE,
                    newUri
            );
        } else {
            RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION, newUri);
        }
        return true;
    }
}
