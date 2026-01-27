import express from 'express';
import dotenv from 'dotenv';
import mongoose from 'mongoose';
import cors from 'cors';

import userRoutes from './routes/user.js'

dotenv.config();

const app = express();

app.use(express.json());
app.use(cors());
app.use('/auth', userRoutes);

mongoose.connect(process.env.MONGO_URI)
    .then(() => {
        app.listen(process.env.PORT, () => {
            console.log("Connected to MongoDB && listening on", process.env.PORT);
        });
    })
    .catch((err) => {
        console.log(err);
    });