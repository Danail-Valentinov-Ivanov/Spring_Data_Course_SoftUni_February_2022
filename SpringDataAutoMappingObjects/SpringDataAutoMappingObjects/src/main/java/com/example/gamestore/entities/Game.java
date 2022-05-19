package com.example.gamestore.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(name = "trailer_id", nullable = false)
    private int trailerId;

    @Column(name = "image_thumbnail_url")
    private String imageThumbnailUrl;

    private float size;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    public Game() {
    }

    public Game(String title, int trailerId, String imageThumbnailUrl
            , float size, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.trailerId = trailerId;
        this.imageThumbnailUrl = imageThumbnailUrl;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(int trailerId) {
        this.trailerId = trailerId;
    }

    public String getImageThumbnailUrl() {
        return imageThumbnailUrl;
    }

    public void setImageThumbnailUrl(String imageThumbnailUrl) {
        this.imageThumbnailUrl = imageThumbnailUrl;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
