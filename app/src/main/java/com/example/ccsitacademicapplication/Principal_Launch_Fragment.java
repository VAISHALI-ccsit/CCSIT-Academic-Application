package com.example.ccsitacademicapplication;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class Principal_Launch_Fragment extends Fragment {

    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReference("All Data").child("BCA");
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    ArrayList<DataClass> dataClasses = new ArrayList<>();
    FloatingActionButton floatingActionButton;
    ProgressBar progressBar;
    String notification_2;
    Context context;
    ImageButton imageButton;
    RecyclerView recyclerView;
    private Uri imageuri;
    Principal_Launch_Adapter ad;
    ImageView imageview;
    ActivityResultLauncher<Intent> activityResultLauncher;

    public Principal_Launch_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActivityResultLauncher<Intent> activityResultLauncher =
       activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if (result.getResultCode()== RESULT_OK){
                                    Intent data = result.getData();
                                    imageuri=data.getData();
                                    imageview.setImageURI(imageuri);
                                }else {
                                    Toast.makeText(getContext(), "No Image Selected", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_principal__launch_, container,
                false);

        floatingActionButton = v.findViewById(R.id.floating_but);
        recyclerView=v.findViewById(R.id.principal_recycleview);

       // progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.sample_launch_principal);
                progressBar=dialog.findViewById(R.id.progressBar);
                progressBar.setVisibility(View.INVISIBLE);
                EditText editText = dialog.findViewById(R.id.edit_notification);
                imageview = dialog.findViewById(R.id.img_add);
                //imageButton=dialog.findViewById(R.id.Add_pdf_icon_byrecycleview);
                ImageButton close_dialog = dialog.findViewById(R.id.close_dialog);

                /*imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { //PDF send
                        selectPDF_ALL();
                    }

                    private void selectPDF_ALL() {
                        Intent intent = new Intent();//this intent pick file from storage
                        intent.setType("application/pdf");
                        intent.setAction(intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),12); //1st file will be store here before storing in firebase
                    }
                });*/


                close_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });

                Button action_all_but = dialog.findViewById(R.id.launch_principal);

                Button bca_send = dialog.findViewById(R.id.bca_laungh);
                Button mca_send = dialog.findViewById(R.id.mca_launch);
                Button btech_send = dialog.findViewById(R.id.btech_lanch);
                Button mtech_send = dialog.findViewById(R.id.mtech_launch);
                Button bsc_send = dialog.findViewById(R.id.bsc_launch);
                Button msc_send = dialog.findViewById(R.id.msc_launch);

                /*Button update = dialog.findViewById(R.id.update_principal);
                Button delete = dialog.findViewById(R.id.delete_principal);
*/

                imageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent photoPicker = new Intent();
                        photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                        photoPicker.setType("image/*");
                        activityResultLauncher.launch(photoPicker);
                    }
                });

                action_all_but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DialogFragment dialogFragment = new DialogFragment();
                        dialogFragment.show(getFragmentManager(), "DialogFragmnet");


                        if(imageuri!=null){
                            uploadToFirebase(imageuri);
                        }else{
                            Toast.makeText(getContext(), "Image not select here", Toast.LENGTH_SHORT).show();
                        }

                        /*if(notification!=null ){

                            DatabaseReference ref_bca = FirebaseDatabase.getInstance().getReference("All Data").child("BCA").child(notification);
                            ref_bca.setValue(notification);
                            DatabaseReference ref_mca = FirebaseDatabase.getInstance().getReference("All Data").child("MCA").child(notification);
                            ref_mca.setValue(notification);
                            DatabaseReference ref_btech = FirebaseDatabase.getInstance().getReference("All Data").child("BTech").child(notification);
                            ref_btech.setValue(notification);
                            DatabaseReference ref_mtech = FirebaseDatabase.getInstance().getReference("All Data").child("MTech").child(notification);
                            ref_mtech.setValue(notification);
                            DatabaseReference ref_bsc = FirebaseDatabase.getInstance().getReference("All Data").child("BSc").child(notification);
                            ref_bsc.setValue(notification);
                            DatabaseReference ref_msc = FirebaseDatabase.getInstance().getReference("All Data").child("MSc").child(notification);
                            ref_msc.setValue(notification);

                            // String userid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                             // DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                              //DatabaseReference uidref = rootRef.child("All Data").child(userid);
                             // uidref.setValue(notification);



                            Toast.makeText(getContext(), "Data Sent", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(context, "No notification", Toast.LENGTH_SHORT).show();
                        }*/


                        /*arr_contactModels.add(new ContactModel(notification));
                        ad.notifyItemInserted(arr_contactModels.size()-1);
                        recyclerView.scrollToPosition(arr_contactModels.size()-1);*/


                        dialog.dismiss();
                    }

                    private void uploadToFirebase(Uri uri) {
                        notification_2 = editText.getText().toString();

                        if(uri !=null){


                            final StorageReference imageReference = storageReference.child(System.currentTimeMillis()+
                                    "."+ getFileExtension(uri));

                            imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                                      //DataClass dataClass = new DataClass(uri.toString(), notification_2, uri.toString());
                                     DataClass dataClass = new DataClass(uri.toString(), notification_2);
                                     DatabaseReference ref_bca = FirebaseDatabase.getInstance().getReference("All Data").child("BCA").child(notification_2);
                                     ref_bca.setValue(dataClass);

                                    DatabaseReference ref_mca = FirebaseDatabase.getInstance().getReference("All Data").child("MCA").child(notification_2);
                                    ref_mca.setValue(dataClass);
                                    DatabaseReference ref_btech = FirebaseDatabase.getInstance().getReference("All Data").child("BTech").child(notification_2);
                                    ref_btech.setValue(dataClass);
                                    DatabaseReference ref_mtech = FirebaseDatabase.getInstance().getReference("All Data").child("MTech").child(notification_2);
                                    ref_mtech.setValue(dataClass);
                                    DatabaseReference ref_bsc = FirebaseDatabase.getInstance().getReference("All Data").child("BSc").child(notification_2);
                                    ref_bsc.setValue(dataClass);
                                    DatabaseReference ref_msc = FirebaseDatabase.getInstance().getReference("All Data").child("MSc").child(notification_2);
                                    ref_msc.setValue(dataClass);

                                        progressBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();




                                }
                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                                    progressBar.setVisibility(View.VISIBLE);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Log.e(TAG, "Upload Failed:" + e.getMessage());
                                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(context, "Please select an image . vaishali....", Toast.LENGTH_SHORT).show();
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        dataClasses.add(new DataClass(imageuri.toString(),notification_2,uri.toString() ));
                        ad.notifyItemInserted(dataClasses.size()-1);//
                        recyclerView.scrollToPosition(dataClasses.size()-1);
                        ad.notifyDataSetChanged();

                        dialog.dismiss();


                    }




                });


                bca_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(imageuri!=null){
                           uploadImageToBCA(imageuri);
                        }else{
                            Toast.makeText(getContext(), "Please Select image", Toast.LENGTH_SHORT).show();
                        }

                    }

                    private void uploadImageToBCA(Uri uri) {

                        String notification_2 = editText.getText().toString();

                        if(uri !=null){


                            final StorageReference imageReference = storageReference.child(System.currentTimeMillis()+
                                    "."+ getFileExtension(uri));

                            imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    DataClass dataClass = new DataClass(uri.toString(), notification_2);
                                    DatabaseReference ref_bca = FirebaseDatabase.getInstance().getReference("All Data").child("BCA").child(notification_2);
                                    ref_bca.setValue(dataClass);

                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                                    progressBar.setVisibility(View.VISIBLE);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Log.e(TAG, "Upload Failed:" + e.getMessage());
                                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(context, "Please select an image . vaishali....", Toast.LENGTH_SHORT).show();
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        dataClasses.add(new DataClass(imageuri.toString(),notification_2));
                        ad.notifyItemInserted(dataClasses.size()-1);//
                        recyclerView.scrollToPosition(dataClasses.size()-1);
                        ad.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });

                mca_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(imageuri!=null){
                            uploadImageTo_MCA(imageuri);
                        }else{
                            Toast.makeText(getContext(), "Please Select image", Toast.LENGTH_SHORT).show();
                        }
                    }

                    private void uploadImageTo_MCA(Uri uri) {
                        String notification_2 = editText.getText().toString();

                        if(uri !=null){


                            final StorageReference imageReference = storageReference.child(System.currentTimeMillis()+
                                    "."+ getFileExtension(uri));

                            imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    DataClass dataClass = new DataClass(uri.toString(), notification_2);
                                    DatabaseReference ref_mca = FirebaseDatabase.getInstance().getReference("All Data").child("MCA").child(notification_2);
                                    ref_mca.setValue(dataClass);

                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                                    progressBar.setVisibility(View.VISIBLE);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Log.e(TAG, "Upload Failed:" + e.getMessage());
                                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(context, "Please select an image . vaishali....", Toast.LENGTH_SHORT).show();
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        dataClasses.add(new DataClass(imageuri.toString(),notification_2));
                        ad.notifyItemInserted(dataClasses.size()-1);//
                        recyclerView.scrollToPosition(dataClasses.size()-1);
                        ad.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });

                btech_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        uploadImageTo_BTech(imageuri);

                    }

                    private void uploadImageTo_BTech(Uri uri) {
                        String notification_2 = editText.getText().toString();

                        if(uri !=null){


                            final StorageReference imageReference = storageReference.child(System.currentTimeMillis()+
                                    "."+ getFileExtension(uri));

                            imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    DataClass dataClass = new DataClass(uri.toString(), notification_2);
                                    DatabaseReference ref_btech = FirebaseDatabase.getInstance().getReference("All Data").child("BTech").child(notification_2);
                                    ref_btech.setValue(dataClass);

                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                                    progressBar.setVisibility(View.VISIBLE);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Log.e(TAG, "Upload Failed:" + e.getMessage());
                                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(context, "Please select an image . vaishali....", Toast.LENGTH_SHORT).show();
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        dataClasses.add(new DataClass(imageuri.toString(),notification_2));
                        ad.notifyItemInserted(dataClasses.size()-1);//
                        recyclerView.scrollToPosition(dataClasses.size()-1);
                        ad.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });
                mtech_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        uploadImageTo_MTech(imageuri);
                        String notification = editText.getText().toString();
                        //imageview.setImageResource(R.drawable.ic_launcher_background);

                    }

                    private void uploadImageTo_MTech(Uri uri) {
                        String notification_2 = editText.getText().toString();

                        if(uri !=null){


                            final StorageReference imageReference = storageReference.child(System.currentTimeMillis()+
                                    "."+ getFileExtension(uri));

                            imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    DataClass dataClass = new DataClass(uri.toString(), notification_2);
                                    DatabaseReference ref_mtech = FirebaseDatabase.getInstance().getReference("All Data").child("MTech").child(notification_2);
                                    ref_mtech.setValue(dataClass);


                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                                    progressBar.setVisibility(View.VISIBLE);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Log.e(TAG, "Upload Failed:" + e.getMessage());
                                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(context, "Please select an image . vaishali....", Toast.LENGTH_SHORT).show();
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        dataClasses.add(new DataClass(imageuri.toString(),notification_2));
                        ad.notifyItemInserted(dataClasses.size()-1);//
                        recyclerView.scrollToPosition(dataClasses.size()-1);
                        ad.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });

                bsc_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        uploadImageTo_BSc(imageuri);
                    }

                    private void uploadImageTo_BSc(Uri uri) {

                        String notification_2 = editText.getText().toString();

                        if(uri !=null){


                            final StorageReference imageReference = storageReference.child(System.currentTimeMillis()+
                                    "."+ getFileExtension(uri));

                            imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    DataClass dataClass = new DataClass(uri.toString(), notification_2);
                                    DatabaseReference ref_bca = FirebaseDatabase.getInstance().getReference("All Data").child("BSc").child(notification_2);
                                    ref_bca.setValue(dataClass);

                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                                    progressBar.setVisibility(View.VISIBLE);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Log.e(TAG, "Upload Failed:" + e.getMessage());
                                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(context, "Please select an image . vaishali....", Toast.LENGTH_SHORT).show();
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        dataClasses.add(new DataClass(imageuri.toString(),notification_2));
                        ad.notifyItemInserted(dataClasses.size()-1);//
                        recyclerView.scrollToPosition(dataClasses.size()-1);
                        ad.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });

                msc_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        uploadImageTo_MSc(imageuri);
                    }

                    private void uploadImageTo_MSc(Uri uri) {
                        String notification_2 = editText.getText().toString();

                        if(uri !=null){


                            final StorageReference imageReference = storageReference.child(System.currentTimeMillis()+
                                    "."+ getFileExtension(uri));

                            imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    DataClass dataClass = new DataClass(uri.toString(), notification_2);
                                    DatabaseReference ref_bca = FirebaseDatabase.getInstance().getReference("All Data").child("MSc").child(notification_2);
                                    ref_bca.setValue(dataClass);

                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                                    progressBar.setVisibility(View.VISIBLE);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Log.e(TAG, "Upload Failed:" + e.getMessage());
                                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(context, "Please select an image . vaishali....", Toast.LENGTH_SHORT).show();
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        dataClasses.add(new DataClass(imageuri.toString(),notification_2));
                        ad.notifyItemInserted(dataClasses.size()-1);//
                        recyclerView.scrollToPosition(dataClasses.size()-1);
                        ad.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });


                dialog.show();

            }

        }); //Float button end

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        ad = new Principal_Launch_Adapter(this, dataClasses); //Display all data of arr_contactModels in Recycleview
        recyclerView.setAdapter(ad);



        //recycleViewState = recyclerView.getLayoutManager().onSaveInstanceState();

       // recyclerView.getLayoutManager().onRestoreInstanceState(recycleViewState);


        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       /* button.setEnabled(true);
        editText.setText(data.getDataString()
                .substring(data.getDataString().lastIndexOf("/")+1));
*/
        if(requestCode==12 && resultCode==RESULT_OK && data!=null &&
        data.getData()!=null){
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadPDF_File_Firebase_All(data.getData());
                }

                private void uploadPDF_File_Firebase_All(Uri data) {

                    /*final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setTitle("File is loading....");
                    progressDialog.show();*/

                    StorageReference reference = storageReference.child("All Data").child("upload"+System.currentTimeMillis()+".pdf");
                    reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uriTask.isComplete());
                            Uri url_pdf = uriTask.getResult();

                            DataClass dataClass = new DataClass(data.toString(), notification_2, url_pdf.toString());
                            databaseReference.child(databaseReference.push().getKey()).setValue(dataClass);
                            Toast.makeText(getContext(), "File is uploaded", Toast.LENGTH_SHORT).show();
                           // progressDialog.dismiss();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                            //double progress = (100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                            //progressBar.setMessage("File Uploaded...." + (int)progress+ "%");
                        }
                    });
                }
            });
        }
    }

    private String getFileExtension(Uri fileUri) {
        ContentResolver contentResolver = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}