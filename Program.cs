using System;


namespace Challenge5
{
    class Program{

        static void Main(string[] args){

            List<PeopleTable> table = new List<PeopleTable>();

            table.Add(new PeopleTable() {name = "Arjun", dob = Convert.ToDateTime("2020/10/28") ,nationality = "Australia"});
            table.Add(new PeopleTable() {name = "Arjun", dob = Convert.ToDateTime("2020/05/28"),nationality = "Australia"});
            table.Add(new PeopleTable() {name = "Arjun", dob = Convert.ToDateTime("2020/10/28"),nationality = "India"});
            table.Add(new PeopleTable() {name = "Dinesh", dob = Convert.ToDateTime("1992/02/09"),nationality = "India"});
            table.Add(new PeopleTable() {name = "Dinesh", dob = Convert.ToDateTime("2020/10/28"),nationality = "NewZealand"});
            table.Add(new PeopleTable() {name = "Krithik", dob = Convert.ToDateTime("2020/10/28"),nationality = "Australia"});


            List<PeopleTable> dupTable = duplicateTable(table);

            Console.WriteLine("------------------------");
            Console.WriteLine("Duplicate table");
            Console.WriteLine("------------------------");
            foreach(PeopleTable k in dupTable ){

                Console.WriteLine("{0}: {1}: {2}", k.name, k.dob.ToShortDateString(), k.nationality);  
            }

            table = table.DistinctBy(x => x.name).ToList();

            Console.WriteLine("------------------------");
            Console.WriteLine("Altered table with unique names");
            Console.WriteLine("------------------------");
            foreach(PeopleTable k in table ){

                Console.WriteLine("{0}: {1}: {2}", k.name, k.dob.ToShortDateString(), k.nationality);  
            }

            int avgage = averageAge(table);

            Console.WriteLine("------------------------");
            Console.WriteLine("average age: {0}",avgage);

            int n = 30;
            List<PeopleTable> tableAge = table.FindAll(x => 
            new DateTime(DateTime.Now.Subtract(x.dob).Ticks).Year - 1 < n);

            Console.WriteLine("------------------------");
            Console.WriteLine("Table with age less than {0}",n );
            Console.WriteLine("------------------------");
            foreach(PeopleTable k in tableAge ){

                Console.WriteLine("{0}: {1}: {2}", k.name, k.dob.ToShortDateString(), k.nationality);  
            }
            
            List<PeopleTable> tableCountry = table.DistinctBy(x => x.nationality).ToList();

            Console.WriteLine("------------------------");
            Console.WriteLine("Table with different countries",n );
            Console.WriteLine("------------------------");
            foreach(PeopleTable k in tableCountry ){

                Console.WriteLine("{0}: {1}: {2}", k.name, k.dob.ToShortDateString(), k.nationality);  
            }

        }

        static List<PeopleTable> duplicateTable(List<PeopleTable> table1){
            List<PeopleTable> dupTable = new List<PeopleTable>();

                for( int p=0; p < table1.Count;  p++){

                    for(int j=p+1 ; j< table1.Count; j++){

                        if(table1[j].name==table1[p].name && 
                            !dupTable.Exists(x => x.name == table1[j].name && x.dob == table1[j].dob 
                            && x.nationality == table1[j].nationality)){
                            
                                dupTable.Add(new PeopleTable() 
                                    {name = table1[j].name, dob = table1[j].dob, nationality = table1[j].nationality });
                        }
                    }
                }


            return dupTable ;
        }

       
        static int averageAge(List<PeopleTable> table1){
            int age=0, count =0;
            foreach(PeopleTable p in table1)
            {
                count++;
                age += new DateTime(DateTime.Now.Subtract(p.dob).Ticks).Year - 1; 
            }

            int avgage = (int) age/count;
            

            return avgage;
        }
    }
    class PeopleTable{
       public string? name;
        //public string? dob;
        public DateTime dob;

        public string? nationality;


    }
}
