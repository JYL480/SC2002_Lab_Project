// // public class Sorting
// // {
// // 	public static void insertionSort (Comparable[] list)
// // 	{
// // 		for (int index = 1; index < list.length; index++)
// // 		{
// // 			Comparable key = list[index];
// // 			int position = index;
// // 			// Shift larger values to the right
// // 			while (position > 0 && key.compareTo(list[position-1]) > 0)
// // 			{
// // 				list[position] = list[position-1];
// // 				position--;
// // 			}
// // 				list[position] = key;
// // 		}
// // 	}
// // }

// import java.util.ArrayList;
// public class Sorting
// {
//     public static void insertionSort(ArrayList<Attendee> names) {
//         for (int index = 1; index < names.size(); index++) {
//             String key = names.get(0)[0];
//             int position = index;

//             // Shift larger values to the right
//             while (position > 0 && key.compareTo(names.get(position - 1)) < 0) {
//                 names.set(position, names.get(position - 1));
//                 position--;
//             }

//             names.set(position, key);
//         }
//     }
// }

