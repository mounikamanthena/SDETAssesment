using System;
using System.Collections.Generic;

namespace PlanitTesting{
    class Challenge2
    {

        public static void Main(string[] args){

            string name = "Haracterc0";
            string nameLowerCase = name.ToLower();
            Dictionary<char,int> charCount = new Dictionary<char, int>();

            for(int i=0; i<nameLowerCase.Length; i++){
                int count=1;

                    for(int j=i+1; j<nameLowerCase.Length; j++){
                        
                        if(nameLowerCase[i]==nameLowerCase[j] && !charCount.ContainsKey(nameLowerCase[i])){
                            
                            count++;
                        }
                    }
                if(!charCount.ContainsKey(nameLowerCase[i])){
                    charCount.Add(nameLowerCase[i],count);
                }
            }
            var maxCountChar = charCount.Aggregate((x,y) => x.Value >= y.Value ? x : y  ).Key;
            Console.WriteLine(maxCountChar);

            // for(int k=0; k<charCount.Count; k++){
            //     Console.WriteLine($"Key: {charCount.ElementAt(k).Key} value: {charCount.ElementAt(k).Value}");
            // }


        }
    }
}
