import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import s from './SneakerDetails.module.css';
import Dropdown from './Dropdown';
import { HeartFilled, HeartOutlined } from '@ant-design/icons';

const SneakerDetails = () => {
    const [sneaker, setSneaker] = useState(null);
    const [size, setSize] = useState(null);
    const { id } = useParams();

    const [wishHovered, setWishHovered] = useState(false);

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
                    <Dropdown 
                        value={size}
                        setValue={setSize}
                        options={[36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52]}
                        placeholder={'Select size'}
                    />
                    <div className={s.bagOrWishlist}>
                        <div className={s.bag}>
                            <p>Add to Cart</p>
                        </div>
                        <div
                            className={s.wish}
                            onMouseEnter={() => setWishHovered(true)}
                            onMouseLeave={() => setWishHovered(false)}
                        >
                            <span className={s.wishHeart}>
                                {wishHovered ? <HeartFilled /> : <HeartOutlined />}
                            </span>
                        </div>
                    </div>
                    <p className={s.sneakerPrice}>${sneaker?.price}</p>
                </div>
            </div>
        </div>
    );
}

export default SneakerDetails;