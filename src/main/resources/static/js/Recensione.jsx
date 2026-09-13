const { useEffect, useState } = React;

function Recensione({ userId }) {

    const [reviews, setReviews] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {

        fetch(`/api/registered/${userId}/recensioni`)
            .then(response => {

                if (!response.ok) {
                    throw new Error("Errore nel caricamento delle recensioni");
                }

                return response.json();
            })
            .then(data => {
                setReviews(data);
                setLoading(false);
            })
            .catch(error => {
                console.error(error);
                setError(error.message);
                setLoading(false);
            });

    }, [userId]);


    const eliminaRecensione = async (id) => {

        const conferma = window.confirm(
            "Sei sicuro di voler eliminare questa recensione?"
        );

        if (!conferma) {
            return;
        }

        try {

			const csrfToken = document
			    .querySelector('meta[name="_csrf"]')
			    .getAttribute("content");

			const csrfHeader = document
			    .querySelector('meta[name="_csrf_header"]')
			    .getAttribute("content");

			const response = await fetch(
			    `/api/recensione/${id}`,
			    {
			        method: "DELETE",
			        headers: {
			            [csrfHeader]: csrfToken
			        }
			    }
			);


            if (!response.ok) {
                throw new Error(
                    "Errore durante l'eliminazione della recensione"
                );
            }

            setReviews(
                reviews.filter(review => review.id !== id)
            );

        } catch (error) {

            console.error(error);

            alert(
                "Si è verificato un errore durante l'eliminazione."
            );
        }
    };


    if (loading) {
        return (
            <p className="empty-message">
                Caricamento recensioni...
            </p>
        );
    }


    if (error) {
        return (
            <p className="auth-error">
                {error}
            </p>
        );
    }


	if (reviews.length === 0) {
	    return (
	        <div>

	            <div className="review-page-header">

	                <h1>Le tue recensioni</h1>

	                <a
	                    className="film-link"
	                    href="/recensione/nuova"
	                >
	                    + Nuova recensione
	                </a>

	            </div>

	            <div className="review-list">

	                <p className="empty-message">
	                    Non ci sono ancora recensioni a tuo nome.
	                </p>

	            </div>

	        </div>
	    );
	}


	return (
	    <div>

	        <div className="review-page-header">

	            <h1>Le tue recensioni</h1>

	            <a
	                className="film-link"
	                href="/recensione/nuova"
	            >
	                + Nuova recensione
	            </a>

	        </div>


	        <div className="review-list">

	            {reviews.map(review => (

	                <article
	                    className="review-card"
	                    key={review.id}
	                >

	                    <div className="review-header">

	                        <span className="review-date">
	                            {review.date}
	                        </span>

	                        <span className="review-date">
	                            {review.filmTitle}
	                        </span>

	                        <div className="review-actions">

	                            <span className="review-vote">
	                                ⭐ {review.vote}/5
	                            </span>

	                            <a
	                                className="film-link"
	                                href={`/recensione/${review.id}/mod`}
	                            >
	                                modifica
	                            </a>

	                            <button
	                                className="film-link"
	                                onClick={() =>
	                                    eliminaRecensione(review.id)
	                                }
	                            >
	                                elimina
	                            </button>

	                        </div>

	                    </div>

	                    <p className="review-text">
	                        {review.text}
	                    </p>

	                </article>

	            ))}

	        </div>

	    </div>
	);


}
