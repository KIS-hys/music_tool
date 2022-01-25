package com.example.client.view;

import com.example.rmi.data.Music;
import com.example.rmi.service.RMIService;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.swagger.v3.oas.models.links.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.awt.Label;
import java.util.List;


@Route
@PageTitle("Video | Video Search")
public class MainView extends VerticalLayout {
    @Value("${ServerUrl:http://localhost:8080}")
    String ServerUrl;
    TextField filterText = new TextField();
    Grid<Music> grid = new Grid<>(Music.class);
    List<Music> musics;


    @Autowired
    RMIService rmiService;

    @PostConstruct
    public void start() {
        addClassName("video");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
        updateList();
    }


    public void updateList() {
        musics = rmiService.getAllMusic();
        grid.setItems(this.musics);
    }
    public void update() {
        grid.setItems(rmiService.getMusicByName(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassNames("music");
        grid.setSizeFull();
        grid.removeAllColumns();

        grid.addColumn(Music::getName).setHeader("music name");
        grid.addComponentColumn(Music -> new Anchor(ServerUrl + Music.getUrl(), Music.getUrl())).setHeader("music url");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void test() {
        VerticalLayout layout = new VerticalLayout();

        Label label = new Label("test");
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter video name");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> filterText.getValue());

        Button enterButton = new Button("enter");
        enterButton.addClickListener(click -> update());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, enterButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}