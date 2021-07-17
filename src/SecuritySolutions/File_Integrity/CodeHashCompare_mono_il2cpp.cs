using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.IO;
using System.Security.Cryptography;
using System;
using System.Text;

public class CodeHashCompare_mono_il2cpp : MonoBehaviour
{

    //검사할 파일 이름
#if ENABLE_MONO
    static readonly string FileName = "CodeHashCompare_Data/Managed/Assembly-CSharp.dll";
#endif


#if ENABLE_IL2CPP
    static readonly string FileName = "GameAssembly.dll";
    static readonly string FileName2 = "CodeHashCompare_Data/il2cpp_Data/Metadata/global-metadata.dat";
#endif

    // Start is called before the first frame update
    void Start()
    {
#if ENABLE_MONO
        FileInfo f1 = new FileInfo(Path.Combine(Path.GetFullPath(Application.dataPath + @"\..\"), FileName));
        FileInfo f2 = new FileInfo(Path.Combine(Path.GetFullPath(Application.dataPath + @"\..\"), "Assembly-CSharp_saved.dll"));    //서버에서... 

        if (FilesAreEqual_Hash(f1, f2) == true)
        {
            Debug.Log("Assembly-CSharp.dll 해시가 같음");
        }
        if (FilesAreEqual_Hash(f1, f2) == false)
        {
            Debug.Log("Assembly-CSharp.dll 해시다름");
            //Application.Quit();
        }
#endif


#if ENABLE_IL2CPP
        FileInfo f1 = new FileInfo(Path.Combine(Path.GetFullPath(Application.dataPath + @"\..\"), FileName));
        FileInfo f2 = new FileInfo(Path.Combine(Path.GetFullPath(Application.dataPath + @"\..\"), "GameAssembly_saved.dll"));

        if (FilesAreEqual_Hash(f1, f2) == true)
        {
            Debug.Log("GameAssembly.dll 해시가 같음");
        }
        if (FilesAreEqual_Hash(f1, f2) == false)
        {
            Debug.Log("GameAssembly.dll 해시다름");
            //Application.Quit();
        }

        FileInfo f3 = new FileInfo(Path.Combine(Path.GetFullPath(Application.dataPath + @"\..\"), FileName2));
        FileInfo f4 = new FileInfo(Path.Combine(Path.GetFullPath(Application.dataPath + @"\..\"), "global-metadata_saved.dat"));

        if (FilesAreEqual_Hash(f3, f4) == true)
        {
            Debug.Log("global-metadata 해시가 같음");
        }
        if (FilesAreEqual_Hash(f3, f4) == false)
        {
            Debug.Log("global-metadata 해시다름");
            //Application.Quit();
        }
#endif
    }

    static bool FilesAreEqual_Hash(FileInfo first, FileInfo second)
    {

        byte[] firstHash = SHA256.Create().ComputeHash(first.OpenRead());
        byte[] secondHash = SHA256.Create().ComputeHash(second.OpenRead());
        string f1Hash = Encoding.Default.GetString(firstHash);
        string f2Hash = Encoding.Default.GetString(secondHash);
        Debug.Log(f1Hash);
        Debug.Log(f2Hash);
        Debug.Log(firstHash[7]);
        Debug.Log(secondHash[7]);

        for (int i = 0; i < firstHash.Length; i++)
        {
            if (firstHash[i] != secondHash[i])
                return false;
        }
        return true;
    }
}