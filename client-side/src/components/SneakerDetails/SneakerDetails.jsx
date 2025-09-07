import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import s from './SneakerDetails.module.css';

const SneakerDetails = () => {
    const [sneaker, setSneaker] = useState(null);
    const { id } = useParams();

    useEffect(() => {
        const fetchSneaker = async () => {
            try {
                const response = await fetch(`http://localhost:8080/sneaker?id=${id}`);
                const json = await response.json();

                if (!response.ok) {
                    console.error(json.error);
                } else {
                    setSneaker(json);
                }
            } catch (err) {
                console.error(err);
            }
        }

        fetchSneaker();
    }, [id]);

    return (
        <div className={s.sneakerDetailsContainer}>
            <div className={s.sneakerContainerTwoFr}>
                <div className={s.sneakerBigImgContainer}>
                    <div className={s.sneakerBigImgs}>
                        <img src={sneaker?.images?.[0]} />
                    </div>
                    <div className={s.sneakerBigImgs}>
                        <img src={sneaker?.images?.[2]} />
                    </div>
                </div>
                <div className={s.sneakerSmallImgContainer}>
                    <div className={s.sneakerSmallImgs}>
                        <img src={sneaker?.images?.[1]} />
                    </div>
                    <div className={s.sneakerSmallImgs}>
                        <img src={sneaker?.images?.[3]} />
                    </div>
                    <div className={s.sneakerSmallImgs}>
                        <img src={sneaker?.images?.[4]} />
                    </div>
                </div>
            </div>
            <div className={s.sneakerContainerOneFr}>
                <div className={s.sneakerDesc}>
                    <p className={s.sneakerBrand}>{sneaker?.brand}</p>
                    <p className={s.sneakerName}>{sneaker?.name}</p>
                    <label for="size">Select size:</label>
                    <input list="sizes" id="size" name="size" placeholder="Choose size"/>

                    <datalist id="sizes">
                    <option value="Small"/>
                    <option value="Medium"/>
                    <option value="Large"/>
                    <option value="Extra Large"/>
                    </datalist>

                    <p className={s.sneakerPrice}>${sneaker?.price}</p>
                </div>
            </div>
        </div>
    );
}

export default SneakerDetails;