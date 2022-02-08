package domain;

import java.util.ArrayList;

public class MovieResponseDTO {
    private ArrayList<MovieDTO> items;

    public ArrayList<MovieDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<MovieDTO> items) {
        this.items = items;
    }
}
