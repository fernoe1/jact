import dotenv from 'dotenv';
dotenv.config();
import express from 'express';
import mongoose from 'mongoose';
import cors from 'cors';
import nodemailer from 'nodemailer';

import userRoutes from './routes/user.js';
import sneakerRoutes from './routes/sneaker.js';
import basketRoutes from './routes/basket.js';

export const transporter = nodemailer.createTransport({
    host: "smtp.gmail.com",
    port: 587,
    secure: false, 
    auth: {
        user: process.env.SMTP_GMAIL,
        pass: process.env.SMTP_GMAIL_PASS
    }
})

const app = express();

app.use(express.json());
app.use(cors());
app.use('/users', userRoutes);
app.use('/sneakers', sneakerRoutes)
app.use('/baskets', basketRoutes);


mongoose.connect(process.env.MONGO_URI)
    .then(() => {
        app.listen(process.env.PORT, () => {
            console.log("Connected to MongoDB && listening on", process.env.PORT);
        });
    })
    .catch((err) => {
        console.log(err);
    });