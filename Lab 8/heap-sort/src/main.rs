fn main() {
    let mut arr: Vec<i32> = vec![99, 19, 9, 7, 11, 3, 3, 2, 2, 1];
    println!("Before sort {arr:?}");
    heap_sort(&mut arr);
    println!("After sort: {arr:?}");
}

fn heap_sort(arr: &mut Vec<i32>) {
    let mut end_index = arr.len() - 1;

    swap(arr, 0, end_index);
    end_index -= 1;
    maxify_heap(arr, 0, end_index);

    loop {
        swap(arr, 0, end_index);
        end_index -= 1;
        maxify_heap(arr, 0, end_index);

        if end_index == 1 {
            break;
        }
    }
}

fn maxify_heap(arr: &mut Vec<i32>, start_idx: usize, end_idx: usize) {
    let left_node_idx = 2 * start_idx + 1;
    let right_node_idx = 2 * start_idx + 2;

    let mut largest_index: usize = start_idx;

    if left_node_idx < end_idx && arr[left_node_idx] > arr[start_idx] {
        largest_index = left_node_idx;
    }

    if right_node_idx < end_idx && arr[right_node_idx] > arr[largest_index] {
        largest_index = right_node_idx;
    }

    if largest_index == start_idx {
        return;
    };

    swap(arr, start_idx, largest_index);
    maxify_heap(arr, largest_index, end_idx);
}

fn swap(arr: &mut Vec<i32>, idx1: usize, idx2: usize) {
    let buf = arr[idx1];
    arr[idx1] = arr[idx2];
    arr[idx2] = buf;
}
